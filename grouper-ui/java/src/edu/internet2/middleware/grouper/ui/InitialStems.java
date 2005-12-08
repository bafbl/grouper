/*
Copyright 2004-2005 University Corporation for Advanced Internet Development, Inc.
Copyright 2004-2005 The University Of Bristol

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

package edu.internet2.middleware.grouper.ui;

import edu.internet2.middleware.grouper.*;
import java.util.*;

/**
 * Pluggable interface thats allows site-specific rules for obtaining a list of
 * initial stems to display to users. To use this feature, the key
 * <i>plugin.initialstems </i> in resources/&lt;module&gt;/media.properties must be set to the
 * name of a Class which implements this interface
 * <p />
 * 
 * @author Gary Brown.
 * @version $Id: InitialStems.java,v 1.2 2005-12-08 15:30:19 isgwb Exp $
 */
public interface InitialStems {

	/**
	 * @param s GrouperSession for authenticated user
	 * @return List of stems to display to user
	 * @throws Exception
	 */
	public List getInitialStems(GrouperSession s) throws Exception;
}