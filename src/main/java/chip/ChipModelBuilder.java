/*******************************************************************************
 * Caleydo - Visualization for Molecular Biology - http://caleydo.org
 * Copyright (c) The Caleydo Team. All rights reserved.
 * Licensed under the new BSD license, available at http://caleydo.org/license
 *******************************************************************************/
package chip;

import generic.GenericModelBuilder;
import generic.ImportSpec;

import org.caleydo.vis.lineup.model.RankTableModel;

import demo.project.model.RankTableSpec;

/**
 * @author Samuel Gratzl
 *
 */
public class ChipModelBuilder extends GenericModelBuilder {

	public ChipModelBuilder(ImportSpec spec, RankTableSpec tableSpec) {
		super(spec, tableSpec);
	}

	@Override
	protected void initialColumns(RankTableModel table, String[] headers, ImportSpec spec) {
		ChipView.initialColumns(table, headers, spec);
	}
}
