/*--
	$Header: /home/hagleyj/i2mi/signet/util/SignetXml/xmlstubs/signet/stubs/ChoiceSetImplXb.java,v 1.2 2008-05-17 20:54:09 ddonn Exp $

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
package signet.stubs;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * ChoiceSetImplXb 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChoiceSetImplXb",
		namespace="http://www.internet2.edu/signet",
		propOrder = { "modifyDatetime", "adapterClassName", "choices" }
)
public class ChoiceSetImplXb
{
	@XmlAttribute(name="subsystemId", required=true)
	protected String				subsystemId;

	@XmlAttribute(name="id", required=true)
	protected String				id;

	@XmlAttribute(name="key", required=true)
	protected Integer				key;

	@XmlElement(name="adapterClassName", required=false)
	protected String				adapterClassName;

	@XmlElement(name="modifyDatetime", required=false)
	protected String				modifyDatetime;

	@XmlElement(name="choice", required=false)
	protected List<ChoiceImplXb>	choices;

}