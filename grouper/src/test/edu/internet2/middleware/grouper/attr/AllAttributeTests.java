package edu.internet2.middleware.grouper.attr;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * @author mchyzer
 *
 */
public class AllAttributeTests {

  /**
   * 
   * @return suite
   */
  public static Test suite() {
    TestSuite suite = new TestSuite("Test for edu.internet2.middleware.grouper.attr");
    //$JUnit-BEGIN$
    suite.addTestSuite(AttributeDefTest.class);
    suite.addTestSuite(GroupAttributeSecurityTest.class);
    suite.addTestSuite(AttributeDefNameSetTest.class);
    suite.addTestSuite(AttributeDefNameTest.class);
    suite.addTestSuite(StemAttributeSecurityTest.class);
    suite.addTestSuite(AttributeDefAttributeSecurityTest.class);
    suite.addTestSuite(AttributeAssignValueTest.class);
    suite.addTestSuite(AttributeDefScopeTest.class);
    suite.addTestSuite(AttributeAssignTest.class);
    suite.addTestSuite(MemberAttributeSecurityTest.class);
    //$JUnit-END$
    return suite;
  }

}