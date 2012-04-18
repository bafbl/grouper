/*******************************************************************************
 * Copyright 2012 Internet2
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
/**
 * 
 */
package edu.internet2.middleware.grouper.attr;

import edu.internet2.middleware.grouper.util.GrouperUtil;


/**
 * @author mchyzer
 *
 */
public enum AttributeDefValueType {
  
  /** whole number type, can be used for date/timestamp or other things */
  integer {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return true;
    }
    
  },
  
  /** timestamp stored in integer */
  timestamp {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return true;
    }
    
  },
  
  /** text */
  string {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return true;
    }
    
  },
  
  /** floating point number */
  floating {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return true;
    }
    
  },
  
  /** no value type, the attribute itself is all that is needed */
  marker {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return false;
    }
    
  },
  
  /** this is a reference to a subject in the grouper_members table */
  memberId {

    /**
     * 
     * @see edu.internet2.middleware.grouper.attr.AttributeDefValueType#hasValue()
     */
    @Override
    public boolean hasValue() {
      return true;
    }
    
  };
  
  /**
   * if this type has a value
   * @return true if value, false if not
   */
  public abstract boolean hasValue();
  
  /**
   * do a case-insensitive matching
   * 
   * @param theString
   * @param exceptionOnNull will not allow null or blank entries
   * @return the enum or null or exception if not found
   */
  public static AttributeDefValueType valueOfIgnoreCase(String theString, boolean exceptionOnNull) {
    return GrouperUtil.enumValueOfIgnoreCase(AttributeDefValueType.class, 
        theString, exceptionOnNull);

  }
  
}
