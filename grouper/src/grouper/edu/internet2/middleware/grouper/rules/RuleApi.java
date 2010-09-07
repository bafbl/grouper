package edu.internet2.middleware.grouper.rules;

import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.Stem;
import edu.internet2.middleware.grouper.Stem.Scope;
import edu.internet2.middleware.grouper.attr.assign.AttributeAssign;
import edu.internet2.middleware.grouper.attr.assign.AttributeAssignable;
import edu.internet2.middleware.grouper.attr.value.AttributeValueDelegate;
import edu.internet2.middleware.grouper.privs.Privilege;
import edu.internet2.middleware.grouper.util.GrouperUtil;
import edu.internet2.middleware.subject.Subject;


/**
 * helper methods to assign rules to objects without having to deal with attributes
 * note, you can use this from gsh too
 * @author mchyzer
 */
public class RuleApi {

  /**
   * 
   * @param actAs
   * @param ruleGroup
   * @param mustBeInGroup
   * @param vetoKey
   * @param vetoMessage
   */
  public static void vetoMembershipIfNotIfGroup(Subject actAs, Group ruleGroup, Group mustBeInGroup, String vetoKey, String vetoMessage) {
    //add a rule on stem:a saying if not in stem:b, then dont allow add to stem:a
    AttributeAssign attributeAssign = ruleGroup
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();
    
    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();

    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(), RuleCheckType.membershipAdd.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleIfConditionEnumName(), RuleIfConditionEnum.groupHasNoImmediateEnabledMembership.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleIfOwnerIdName(), mustBeInGroup.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(), RuleThenEnum.veto.name());
    
    //key which would be used in UI messages file if applicable
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg0Name(), vetoKey);
    
    //error message (if key in UI messages file not there)
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg1Name(), vetoMessage);

    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());

    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }

  }

  /**
   * make sure stem privileges are inherited in a attributeDef
   * @param actAs
   * @param stem
   * @param stemScope ONE or SUB
   * @param subjectToAssign
   * @param privileges can use Privilege.getInstances() to convert from string
   */
  public static void inheritAttributeDefPrivileges(Subject actAs, Stem stem, Scope stemScope, 
      Subject subjectToAssign, Set<Privilege> privileges) {
    

    //add a rule on stem2 saying if you create a group underneath, then assign a reader group
    AttributeAssign attributeAssign = stem
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();
    
    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();
    
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(), RuleCheckType.attributeDefCreate.name());

    //can be SUB or ONE for if in this folder, or in this and all subfolders
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckStemScopeName(), stemScope.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(), RuleThenEnum.assignAttributeDefPrivilegeToAttributeDefId.name());

    //this is the subject string for the subject to assign to
    //e.g. sourceId :::::: subjectIdentifier
    //or sourceId :::: subjectId
    //or :::: subjectId
    //or sourceId ::::::: subjectIdOrIdentifier
    //etc
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg0Name(), subjectToAssign.getSourceId()+ " :::: " + subjectToAssign.getId());

    //can be: attrRead, attrUpdate, attrView, attrAdmin, attrOptin, attrOptout
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg1Name(), Privilege.stringValue(privileges));
  
    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());
  
    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }
  }
  
  /**
   * make sure stem privileges are inherited in a stem
   * @param actAs
   * @param stem
   * @param stemScope ONE or SUB
   * @param subjectToAssign
   * @param privileges can use Privilege.getInstances() to convert from string
   */
  public static void inheritFolderPrivileges(Subject actAs, Stem stem, Scope stemScope, 
      Subject subjectToAssign, Set<Privilege> privileges) {
    
    //add a rule on stem2 saying if you create a group underneath, then assign a reader group
    AttributeAssign attributeAssign = stem
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();
    
    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();
  
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(), RuleCheckType.stemCreate.name());
    
    //can be SUB or ONE for if should be in all descendants or just on children
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckStemScopeName(), stemScope.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(), RuleThenEnum.assignStemPrivilegeToStemId.name());
    
    //this is the subject string for the subject to assign to
    //e.g. sourceId :::::: subjectIdentifier
    //or sourceId :::: subjectId
    //or :::: subjectId
    //or sourceId ::::::: subjectIdOrIdentifier
    //etc
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg0Name(), subjectToAssign.getSourceId() + " :::: " + subjectToAssign.getId());
    
    //possible privileges are stem and create
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg1Name(), Privilege.stringValue(privileges));
  
    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());
  
    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }
  }

  
  /**
   * make sure group privileges are inherited in a stem
   * @param actAs
   * @param stem
   * @param stemScope ONE or SUB
   * @param subjectToAssign
   * @param privileges can use Privilege.getInstances() to convert from string
   */
  public static void inheritGroupPrivileges(Subject actAs, Stem stem, Scope stemScope, 
      Subject subjectToAssign, Set<Privilege> privileges) {
    
    //add a rule on stem2 saying if you create a group underneath, then assign a reader and updater group
    AttributeAssign attributeAssign = stem
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();
    
    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();
    
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(), RuleCheckType.groupCreate.name());
    
    //can be SUB or ONE for if in this folder, or in this and all subfolders
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckStemScopeName(), stemScope.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(), RuleThenEnum.assignGroupPrivilegeToGroupId.name());
    
    //this is the subject string for the subject to assign to
    //e.g. sourceId :::::: subjectIdentifier
    //or sourceId :::: subjectId
    //or :::: subjectId
    //or sourceId ::::::: subjectIdOrIdentifier
    //etc
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg0Name(), subjectToAssign.getSourceId() + " :::: " + subjectToAssign.getId());
    
    //privileges to assign: read, admin, update, view, optin, optout
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg1Name(), Privilege.stringValue(privileges));
    
    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());

    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }

  }

  /**
   * if a member is removed from a folder, and has no more memberships in any group in the folder, then
   * remove from the group
   * @param actAs
   * @param ruleGroup
   * @param folder
   * @param stemScope
   */
  public static void groupIntersectionWithFolder(Subject actAs, Group ruleGroup, Stem folder, Stem.Scope stemScope) {
    //add a rule on stem:a saying if you are out of stem:b, then remove from stem:a
    AttributeAssign attributeAssign = ruleGroup
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();
    
    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();
    
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    
    //folder where membership was removed
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckOwnerIdName(), folder.getUuid());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(), 
        RuleCheckType.membershipRemoveInFolder.name());

    //SUB for all descendants, ONE for just children
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckStemScopeName(),
        stemScope.name());
    
    //if there is no more membership in the folder, and there is a membership in the group
    attributeValueDelegate.assignValue(
        RuleUtils.ruleIfConditionEnumName(), 
        RuleIfConditionEnum.thisGroupAndNotFolderHasImmediateEnabledMembership.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(),
        RuleThenEnum.removeMemberFromOwnerGroup.name());

    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());

    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }

  }
  
  /**
   * put a rule on the rule group which says that if the user is not in the mustBeInGroup, 
   * then remove from ruleGroup
   * @param actAs
   * @param ruleGroup
   * @param mustBeInGroup
   */
  public static void groupIntersection(Subject actAs, Group ruleGroup, Group mustBeInGroup) {
    AttributeAssign attributeAssign = ruleGroup
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();

    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();

    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    
    //note "mustBeInGroup" is the group (e.g. employees)
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckOwnerIdName(), mustBeInGroup.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(),
        RuleCheckType.membershipRemove.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleIfConditionEnumName(),
        RuleIfConditionEnum.thisGroupHasImmediateEnabledMembership.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(),
        RuleThenEnum.removeMemberFromOwnerGroup.name());

    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());

    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }
    
    
  }
  
  /**
   * put a rule on the rule group which says that if the user is not in the mustBeInGroup, 
   * then add an end date to the membership in the rule group X days in the future
   * @param actAs
   * @param ruleGroup
   * @param mustBeInGroup
   * @param daysInFutureForDisabledDate
   */
  public static void groupIntersection(Subject actAs, Group ruleGroup, Group mustBeInGroup, int daysInFutureForDisabledDate) {

    AttributeAssign attributeAssign = ruleGroup
      .getAttributeDelegate().addAttribute(RuleUtils.ruleAttributeDefName()).getAttributeAssign();

    AttributeValueDelegate attributeValueDelegate = attributeAssign.getAttributeValueDelegate();

    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectSourceIdName(), actAs.getSourceId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleActAsSubjectIdName(), actAs.getId());
    
    //if the user falls out of mustBeInGroup, then set a disabled date in this group
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckOwnerIdName(), mustBeInGroup.getId());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleCheckTypeName(),
        RuleCheckType.membershipRemove.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleIfConditionEnumName(),
        RuleIfConditionEnum.thisGroupHasImmediateEnabledMembership.name());
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumName(), RuleThenEnum.assignMembershipDisabledDaysForOwnerGroupId.name());
    
    //number of days in future that disabled date should be set
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg0Name(), "7");
    
    //if the membership in owner group doesnt exist, should it be added?  T|F
    attributeValueDelegate.assignValue(
        RuleUtils.ruleThenEnumArg1Name(), "F");

    //should be valid
    String isValidString = attributeValueDelegate.retrieveValueString(
        RuleUtils.ruleValidName());

    if (!StringUtils.equals("T", isValidString)) {
      throw new RuntimeException(isValidString);
    }

  }

  /**
   * 
   * @return the string
   */
  public static String rulesToString() {
    RuleEngine ruleEngine = RuleEngine.ruleEngine();

    StringBuilder result = new StringBuilder();
    int i=0;

    for (RuleDefinition ruleDefinition : ruleEngine.getRuleDefinitions()) {
      
      result.append("Rule " + i + ": ");
      
      result.append(ruleDefinition.toString()).append("\n");

      i++;
    }
    
    return result.toString();
  }

  /**
   * 
   * @param attributeAssignable
   * @return the string
   */
  public static String rulesToString(AttributeAssignable attributeAssignable) {
    
    Set<AttributeAssign> attributeAssigns = attributeAssignable.getAttributeDelegate().retrieveAssignments(RuleUtils.ruleAttributeDefName());

    //remove disabled
    Iterator<AttributeAssign> iterator = GrouperUtil.nonNull(attributeAssigns).iterator();

    
    while (iterator.hasNext()) {
      
      AttributeAssign attributeAssign = iterator.next();
      if (!attributeAssign.isEnabled()) {
        iterator.remove();
      }
      
    }
    
    if (GrouperUtil.length(attributeAssigns) == 0) {
      return "ro rules assigned";
    }
    
    StringBuilder result = new StringBuilder();
    int i=0;
    
    for (AttributeAssign attributeAssign : attributeAssigns) {
      
      result.append("Rule " + i + ": ");
      
      RuleDefinition ruleDefinition = new RuleDefinition(attributeAssign.getId());
      
      result.append(ruleDefinition.toString());
      
      if (i < (GrouperUtil.length(attributeAssigns) -1)) {
        result.append("\n"); //note, should already have a comma on it
      }
      i++;
    }
    return result.toString();
  }
  
  /**
   * run rules for an attribute assignable
   * @param attributeAssignable
   * @return the number of rules ran (note, if not valid or not daemonable then dont run, then that doesnt count)
   */
  public static int runRulesForOwner(AttributeAssignable attributeAssignable) {

    Set<AttributeAssign> attributeAssigns = attributeAssignable.getAttributeDelegate().retrieveAssignments(RuleUtils.ruleAttributeDefName());

    //remove disabled
    Iterator<AttributeAssign> iterator = GrouperUtil.nonNull(attributeAssigns).iterator();
    
    while (iterator.hasNext()) {
      
      AttributeAssign attributeAssign = iterator.next();
      if (!attributeAssign.isEnabled()) {
        iterator.remove();
      }
      
    }
    
    if (GrouperUtil.length(attributeAssigns) == 0) {
      return 0;
    }
    
    int i=0;
    
    for (AttributeAssign attributeAssign : attributeAssigns) {
      
      RuleDefinition ruleDefinition = new RuleDefinition(attributeAssign.getId());
      
      if (ruleDefinition.validate() == null) {
        if (ruleDefinition.runDaemonOnDefinitionIfShould()) {
          i++;
        }
      }
      
      
    }
    return i;
    
  }
  
}
