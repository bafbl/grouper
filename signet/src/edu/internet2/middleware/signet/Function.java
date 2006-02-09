/*--
$Id: Function.java,v 1.9 2006-02-09 10:20:01 lmcrae Exp $
$Date: 2006-02-09 10:20:01 $

Copyright 2006 Internet2, Stanford University

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package edu.internet2.middleware.signet;

import java.util.Set;

import edu.internet2.middleware.subject.Subject;

/**
* Function organizes a group of {@link Permission}s. Each Function is
* intended to correspond to a business-level task that a
* {@link PrivilegedSubject} must perform in order to accomplish some business
* operation.
* 
*/

public interface Function
extends SubsystemPart, HelpText, Name, Comparable
{
  /**
   * Gets the ID of this entity.
   * 
   * @return Returns a short mnemonic id which will appear in XML
   *    documents and other documents used by analysts.
   */
  public String getId();
  
  /**
   * Gets the <code>Category</code> associated with this <code>Function</code>.
   * 
   * @return Returns the category.
   */
  public Category getCategory();

  /**
   * Gets the {@link Permission}s associated with this <code>Function</code>.
   * 
   * @return Returns the permissions.
   */
  public Set getPermissions();
  
  /**
   * Gets the {@link Limit}s associated with this <code>Function</code>'s
   * {@link Permission}s.
   * @return Returns the <code>Set</code> of <code>Limit</code>s.
   */
  public Set getLimits();
  
  /**
   * Adds a <code>Permission</code> to the set of <code>Permission</code>s
   * associated with this <code>Function</code>.
   * 
   * @param permission The <code>Permission</code> to be associated with this
   * <code>Function</code>.
   */
  void addPermission(Permission permission);
}
