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

import java.util.Map;
import java.util.Set;
import edu.internet2.middleware.grouper.GrouperSession;
import edu.internet2.middleware.grouper.Member;
import edu.internet2.middleware.grouper.MemberFinder;

/**
 * Implementation of RepositoryBrowser responsible for 'Join' browse mode
 * <p />
 * 
 * @author Gary Brown.
 * @version $Id: JoinRepositoryBrowser.java,v 1.2 2005-12-08 15:30:19 isgwb Exp $
 */

public class JoinRepositoryBrowser extends AbstractRepositoryBrowser{
	
	
	private Map savedValidStems=null;

	public JoinRepositoryBrowser(){
		prefix = "repository.browser.join.";
		browseMode="Join";
	}
	
	/* (non-Javadoc)
	 * @see edu.internet2.middleware.grouper.ui.AbstractRepositoryBrowser#isValidChild(java.util.Map)
	 */
	protected boolean isValidChild(Map child) throws Exception{
		GrouperSession s = getGrouperSession();
		Map validStems = getValidStems();
		String name = (String) child.get("name");
		if(validStems.containsKey(name)) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.internet2.middleware.grouper.ui.AbstractRepositoryBrowser#getValidStems()
	 */
	protected Map getValidStems() throws Exception{
		Map validStems = savedValidStems;
		if(validStems !=null) return validStems;
		Set groups = null;
		GrouperSession s = getGrouperSession();
		Member member = MemberFinder.findBySubject(s,s.getSubject());
		groups = member.hasOptin();
		
		validStems= getStems(groups);
		return validStems;
	}
}
