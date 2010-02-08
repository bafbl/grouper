/*--
$Id: Choice.java,v 1.9 2007-06-14 21:39:04 ddonn Exp $
$Date: 2007-06-14 21:39:04 $

Copyright 2004 Internet2 and Stanford University.  All Rights Reserved.
Licensed under the Signet License, Version 1,
see doc/license.txt in this distribution.
*/

package edu.internet2.middleware.signet.choice;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents a single possible choice within a ChoiceSet.
 */
public interface Choice extends Comparable, Serializable
{
	/**
	 * Returns the enclosing ChoiceSet of this Choice.
	 */
	public ChoiceSet getChoiceSet() throws ChoiceSetNotFound;
	
	/**
	 * Returns the value associated with this Choice.
	 */
	public String getValue();
	
	/**
	 * Returns a user-friendly representation of the value associated
	 * with this Choice.
	 */
	public String getDisplayValue();
	
	/**
	 * Returns an int that is the relative display order of this
	 * Choice among other Choices within a ChoiceSet.
	 */
	public int getDisplayOrder();

	/**
	 * Returns an int that is used for comparing this Choice 
	 * among other Choices within a ChoiceSet.
	 */
	public int getRank();

	/**
	 * @param choices The Set of Choices to compare the Choice against.
	 * @return true if the rank of the specified Choice does not exceed the rank
	 * of the highest-ranking Choice in the Set, and false otherwise.
	 */
	public boolean doesNotExceed(Set choices);


}