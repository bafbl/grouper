/*--
$Id: TreeNodeRelationship.java,v 1.3 2006-02-09 10:26:36 lmcrae Exp $
$Date: 2006-02-09 10:26:36 $

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

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import edu.internet2.middleware.signet.tree.Tree;
import edu.internet2.middleware.signet.tree.TreeNode;

/**
 * @author acohen
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class TreeNodeRelationship
implements Serializable
{
  private String treeId;
  private String childNodeId;
  private String parentNodeId;
  
  TreeNodeRelationship
  	(String treeId,
  	 String childNodeId,
  	 String parentNodeId)
  {
    super();
    this.treeId = treeId;
    this.childNodeId = childNodeId;
    this.parentNodeId = parentNodeId;
  }
  
  TreeNodeRelationship()
  {
    super();
  }
  
  /**
   * @return Returns the child.
   */
  String getChildNodeId()
  {
    return this.childNodeId;
  }
  /**
   * @param child The child to set.
   */
  private void setChildNodeId(String childNodeId)
  {
    this.childNodeId = childNodeId;
  }
  /**
   * @return Returns the parent.
   */
  String getParentNodeId()
  {
    return this.parentNodeId;
  }
  /**
   * @param parent The parent to set.
   */
  private void setParentNodeId(String parentNodeId)
  {
    this.parentNodeId = parentNodeId;
  }
  
  String getTreeId()
  {
    return this.treeId;
  }
  
  private void setTreeId(String treeId)
  {
    this.treeId = treeId;
  }
  
  
  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  public boolean equals(Object obj)
  {
    if ( !(obj instanceof TreeNodeRelationship) )
    {
      return false;
    }
    
    TreeNodeRelationship rhs = (TreeNodeRelationship) obj;
    return new EqualsBuilder()
    	.append(this.treeId, rhs.treeId)
      .append(this.childNodeId, rhs.childNodeId)
      .append(this.parentNodeId, rhs.parentNodeId)
      .isEquals();
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  public int hashCode()
  {
    // you pick a hard-coded, randomly chosen, non-zero, odd number
    // ideally different for each class
    return new HashCodeBuilder(17, 37)
  		.append(this.treeId)
  		.append(this.childNodeId)
  		.append(this.parentNodeId)
      .toHashCode();
  }
}
