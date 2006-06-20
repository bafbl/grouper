/*
 * Copyright (C) 2006 blair christensen.
 * All Rights Reserved.
 *
 * You may use and distribute under the same terms as Grouper itself.
 */

package com.devclue.grouper.shell;
import  bsh.*;
import  edu.internet2.middleware.grouper.*;
import  edu.internet2.middleware.subject.*;
import  edu.internet2.middleware.subject.provider.*;
import  java.util.*;

/**
 * Query for groups by name.
 * <p/>
 * @author  blair christensen.
 * @version $Id: getGroups.java,v 1.1 2006-06-20 19:53:17 blair Exp $
 * @since   1.0
 */
public class getGroups {

  // PUBLIC CLASS METHODS //

  /**
   * Query for groups by name.
   * <p/>
   * @param   i           BeanShell interpreter.
   * @param   stack       BeanShell call stack.
   * @param   name        Find groups with <i>name</i> as part of their name.
   * @since   1.0
   */
  public static void invoke(Interpreter i, CallStack stack, String name) 
  {
    GroupHelper.getGroups(i, name);
  } // public static void invoke(i, stack, name)

} // public class getGroups

