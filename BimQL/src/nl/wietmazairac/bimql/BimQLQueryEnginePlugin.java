package nl.wietmazairac.bimql;

/******************************************************************************
 * Copyright (C) 2009-2017  BIMserver.org
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see {@literal<http://www.gnu.org/licenses/>}.
 *****************************************************************************/

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bimserver.models.store.ObjectDefinition;
import org.bimserver.plugins.PluginConfiguration;
import org.bimserver.plugins.PluginContext;
import org.bimserver.plugins.queryengine.QueryEngine;
import org.bimserver.plugins.queryengine.QueryEnginePlugin;
import org.bimserver.shared.exceptions.PluginException;

public class BimQLQueryEnginePlugin implements QueryEnginePlugin {

	private final Map<String, String> examples = new LinkedHashMap<String, String>();

	@Override
	public void init(PluginContext pluginContext) throws PluginException {
		examples.put("Return all rooted entities", "Select $Var1");
		examples.put("Return all rooted entities of type IfcDoor", "Select $Var1\nWhere $Var1.EntityType = IfcDoor");
		examples.put("Return all rooted entities of which the OverallHeight attribute equals 2.325", "Select $Var1\nWhere $Var1.Attribute.OverallHeight = 2.325");
		examples.put("Return all rooted entities of which the Volume property equals 3.726407576004009", "Select $Var1\nWhere $Var1.Property.Volume = 3.726407576004009");
		examples.put("Return all rooted entities of which the OverallHeight attribute is between 1.4 and 1.6", "Select $Var1\nWhere $Var1.Attribute.OverallHeight > 1.4 And $Var1.Attribute.OverallHeight < 1.6");
		examples.put("Return all rooted entities of which the OverallHeight attribute is smaller than 1.4 or greater than 1.6", "Select $Var1\nWhere $Var1.Attribute.OverallHeight < 1.4 And $Var1.Attribute.OverallHeight > 1.6");
		examples.put("Return all rooted entities of which the Name attribute starts with Pset", "Select $Var1\nWhere $Var1.Attribute.Name = Pset*");
		examples.put("Return all rooted entities of which the Name attribute end with Level", "Select $Var1\nWhere $Var1.Attribute.Name = *Level");
		examples.put("Return all rooted entities which have a Name attribute", "Select $Var1\nWhere $Var1.Attribute.Name = *");
		examples.put("Return all GlobalId objects which are related to rooted objects of type IfcDoor", "Select $Var1\nWhere $Var1.EntityType = IfcDoor\nSelect $Var2 := $Var1.Attribute.GlobalId");
	}

	@Override
	public QueryEngine getQueryEngine(PluginConfiguration pluginConfiguration) {
		return new BimQLQueryEngine();
	}

	@Override
	public Collection<String> getExampleKeys() {
		return examples.keySet();
	}

	@Override
	public String getExample(String key) {
		return examples.get(key);
	}

	@Override
	public ObjectDefinition getSettingsDefinition() {
		return null;
	}
}