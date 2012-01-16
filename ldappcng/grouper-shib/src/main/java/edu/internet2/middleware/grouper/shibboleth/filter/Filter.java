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

package edu.internet2.middleware.grouper.shibboleth.filter;

import edu.internet2.middleware.grouper.Group;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.filter.QueryFilter;

/**
 * An extension to {@link QueryFilter} which adds the ability to determine if a Group matches (would be returned by) the
 * filter.
 * 
 * @param <T> type of Grouper object to be matched, for example, a {@link Group}.
 */
public interface Filter<T> extends QueryFilter<T> {

  /**
   * Returns true if the Grouper object would be returned by the filter. False otherwise.
   * 
   * @param t the Grouper object
   * @return if the Grouper object matches or not
   */
  public boolean matches(T t);

  /**
   * Set the grouper session
   * 
   * @param grouperSession the {@link GrouperSession}
   */
  public void setGrouperSession(GrouperSession grouperSession);

}