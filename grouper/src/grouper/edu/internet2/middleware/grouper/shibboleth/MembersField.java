/*
 * Copyright (C) 2004-2007 University Corporation for Advanced Internet Development, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package edu.internet2.middleware.grouper.shibboleth;

import java.util.Set;

import edu.internet2.middleware.grouper.Field;
import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.Member;
import edu.internet2.middleware.shibboleth.common.attribute.BaseAttribute;
import edu.internet2.middleware.shibboleth.common.attribute.provider.BasicAttribute;

public class MembersField {

  private String id;

  private Field field;

  private WsMemberFilter memberFilter;

  public MembersField(String id, WsMemberFilter memberFilter, Field field) {
    this.id = id;
    this.memberFilter = memberFilter;
    this.field = field;
  }

  public BaseAttribute<Member> getAttribute(Group group) {

    Set<Member> members = memberFilter.getMembers(group, field);

    if (!members.isEmpty()) {
      BasicAttribute<Member> list = new BasicAttribute<Member>(id);
      list.setValues(members);
      return list;
    }

    return null;
  }

  public String getId() {
    return id;
  }
}