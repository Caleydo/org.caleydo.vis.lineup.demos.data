/*******************************************************************************
 * Caleydo - Visualization for Molecular Biology - http://caleydo.org
 * Copyright (c) The Caleydo Team. All rights reserved.
 * Licensed under the new BSD license, available at http://caleydo.org/license
 *******************************************************************************/
package chip;

import generic.EInferer;
import generic.GenericModelBuilder;
import generic.GenericView;
import generic.ImportSpec;
import generic.ImportSpec.CategoricalColumnSpec;
import generic.ImportSpec.ColumnSpec;
import generic.ImportSpec.DoubleColumnSpec;
import generic.ImportSpec.StringColumnSpec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.caleydo.core.internal.cmd.AOpenViewHandler;
import org.caleydo.core.util.color.Color;
import org.caleydo.core.util.color.ColorBrewer;
import org.caleydo.vis.lineup.model.ARankColumnModel;
import org.caleydo.vis.lineup.model.RankRankColumnModel;
import org.caleydo.vis.lineup.model.RankTableModel;
import org.caleydo.vis.lineup.model.StackedRankColumnModel;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;

import demo.project.model.RankTableSpec;

/*
 * @author Samuel Gratzl
 *
 */
public class ChipView extends GenericView {
	private static final String ID = "lineup.demo.chip";
	private static int PRODUCT;
	private static int PREIS;
	private static int GESAMT;
	private static int PREIS_LEISTUNG;

	private static int AKKU;
	private static int AUSSTATTUNG;
	private static int DISPLAY;
	private static int CAMERA;
	private static int PERFORMANCE;
	private static int TELEFON_UND_SOUND;
	
	private static int PROZESSOR_TAKT;
	private static int RAM;
	private static int SAR;
	private static int GEWICHT;
	private static int DISPLAY_RESOLUTION;
	private static int CAMERA_RESOLUTION;
	private static int FRONT_CAMERA_RESOLUTION;
	
	
	/**
	 *
	 */
	public ChipView() {
		super(createSpec());
	}

	@Override
	protected GenericModelBuilder createBuilder(ImportSpec spec, RankTableSpec tableSpec) {
		return new ChipModelBuilder(spec, tableSpec);
	}

	@Override
	public String getViewGUIID() {
		return ID;
	}
	/**
	 * @return
	 */
	private static ImportSpec createSpec() {
		ImportSpec spec = new ImportSpec();
		Path temp;
		try {
			temp = Files.createTempFile("lineup", ".csv");
			Files.copy(ChipView.class.getResourceAsStream("chip_2015_02.csv"), temp,
					StandardCopyOption.REPLACE_EXISTING);
			spec.setDataSourcePath(temp.toString());
			spec.setDelimiter("\t");
			spec.setLabel("Chip Handy Bestenliste");
			List<ColumnSpec> columns = new ArrayList<>();
			createColumns(columns);
			spec.setColumns(columns);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spec;
	}

	/**
	 * @param columns
	 */
	private static void createColumns(List<ColumnSpec> columns) {
		int i = 0, j = 0;
		Deque<Color> f = new ArrayDeque<>(ColorBrewer.Set3.get(12));
		
		f.addAll(ColorBrewer.Set2.get(8));
		
		//Rang
		i++;
		//Produkt
		PRODUCT = j++;
		columns.add(cstring(i++));
		//Preis
		PREIS = j++;
		columns.add(cdouble(i++, 0, Double.NaN).setColor(f.pollFirst(), null));
		//Gesamt- wertung
		GESAMT = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Preis-Leistung
		PREIS_LEISTUNG = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Akku(15%)
		AKKU = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Ausstattung(20%)
		AUSSTATTUNG = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Display(15%)
		DISPLAY = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Digitalkamera(10%)
		CAMERA = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Performance & Bedienung(30%)
		PERFORMANCE = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Telefon & Sound(10%)
		TELEFON_UND_SOUND = j++;
		columns.add(cdouble(i++, 0, 100).setColor(f.pollFirst(), null));
		//Getestet mit
		i++;
		//Aktuelles Betriebssystem
		i++;
		//Update für Betriebssystem geplant
		i++;
		//App-Store-Anbindung
		i++;
		//Prozessor
		i++;
		//Prozessor-Architektur
		i++;
		//Prozessor-Takt
		PROZESSOR_TAKT = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		//Arbeitsspeicher
		RAM = j++;
		columns.add(cdouble(i++, 0, Double.NaN).setColor(f.pollFirst(), null));
		//SAR-Wert
		SAR = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		//Akku: Sprechzeit
		i++;
		//Akku: Online-Laufzeit
		i++;
		//Induktives Laden
		i++;
		//Akku: Lade-Dauer
		i++;
		//Akku: Kapazität
		i++;
		//Akku: Austauschbar
		i++;
		//Gewicht
		GEWICHT = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		//Höhe x Breite
		i++;
		//Dicke
		i++;
		//WLAN
		i++;
		//GSM-Frequenzbänder
		i++;
		//UMTS: Daten empfangen
		i++;
		//UMTS: Daten versenden
		i++;
		//LTE: Unterstützte Frequenzbänder
		i++;
		//LTE: Cat-4
		i++;
		//LTE: Cat-6
		i++;
		//LTE-Advanced
		i++;
		//Voice over LTE
		i++;
		//Display: Typ
		i++;
		//Display: Diagonale
		i++;
		//Display: Größe in Millimeter
		i++;
		//Display: Auflösung
		DISPLAY_RESOLUTION = j++;
		columns.add(category(i++, "1080 x 1920", "1140 x 2560", "1440 x 2560", "1600 x 2560", "320 x 480", "480 x 800", "480 x 854", "540 x 960", "640 x 1136", "720 x 1080", "720 x 1280", "750 x 1334")
				.setColor(f.pollFirst(), null));
		//Display:dichte
		i++;
		//Display: Helligkeit ohne Umgebungslicht
		i++;
		//Display: Schachbrett-Kontrast
		i++;
		//Status-LED
		i++;
		//SIM-Slot
		i++;
		//Dual-Sim
		i++;
		//IP-Standard
		i++;
		//Fingerprint Reader
		i++;
		//USB OTG
		i++;
		//Speicher
		i++;
		//Speicherkarten-Slot
		i++;
		//USB-Buchse
		i++;
		//Bluetooth
		i++;
		//Infrarot
		i++;
		//NFC
		i++;
		//Kamera: Auflösung
		CAMERA_RESOLUTION = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		//Kamera: Optischer Bildstabilisator
		i++;
		//Kamera: Rauschen
		i++;
		//Kamera: Deadleaves LOW10
		i++;
		//Kamera: Deadleaves FULL10
		i++;
		//Kamera: Farbtreue
		i++;
		//Kamera: Kleinste Brennweite
		i++;
		//Kamera: Verzeichnung
		i++;
		//Kamera: Mindestabstand Makro
		i++;
		//Kamera: Vignettierung
		i++;
		//Kamera: Auslöseverzögerung mit AF
		i++;
		//Kamera: Blickwinkel Frontkamera
		i++;
		//Kamera: Autofokus
		i++;
		//Kamera: Extra Auslösetaste
		i++;
		//Kamera: Blitztyp
		i++;
		//Camcorder-Auflösung
		i++;
		//Front-Kamera: Auflösung
		FRONT_CAMERA_RESOLUTION = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		i++;
		//TV-Ausgang
		i++;
		//Kopfhörer-Ausgang
		i++;
		//Firmware-Version
		i++;
		//Navi: Software
		i++;
		//Navi: Software-Typ
		i++;
		//HD-Voice
		i++;
		//
		i++;
		//

	}

	private static ColumnSpec cdouble(int col, double min, double max) {
		DoubleColumnSpec s = new DoubleColumnSpec();
		s.setCol(col);
		s.setMapping(min, max, EInferer.NaN);
		return s;
	}

	private static ColumnSpec category(int col, String... categories) {
		CategoricalColumnSpec s = new CategoricalColumnSpec(ImmutableSet.copyOf(categories));
		s.setCol(col);
		return s;
	}

	private static ColumnSpec cstring(int col) {
		return new StringColumnSpec(col);
	}

	public static class Handler extends AOpenViewHandler {
		public Handler() {
			super(ID, true);
		}
	}

	/**
	 * @param table
	 * @param headers
	 * @param spec
	 */
	public static void initialColumns(RankTableModel table, final String[] headers, ImportSpec spec) {

		table.add(new RankRankColumnModel());
		if (headers == null)
			return;

		final List<ColumnSpec> cols = spec.getColumns();
		final Function<Integer, ARankColumnModel> create = new Function<Integer, ARankColumnModel>() {
			@Override
			public ARankColumnModel apply(Integer input) {
				return cols.get(input).create(headers, input);
			}
		};

		table.add(create.apply(PRODUCT).setWidth(300));
		table.add(create.apply(PREIS).setWidth(120));
		table.add(create.apply(PREIS_LEISTUNG));

		StackedRankColumnModel s = new StackedRankColumnModel();
		table.add(s);

		
		s.add(create.apply(AKKU).setWidth(15 * 10));
		s.add(create.apply(AUSSTATTUNG).setWidth(20 * 10));
		s.add(create.apply(DISPLAY).setWidth(15 * 10));
		s.add(create.apply(CAMERA).setWidth(10 * 10));
		s.add(create.apply(PERFORMANCE).setWidth(30 * 10));
		s.add(create.apply(TELEFON_UND_SOUND).setWidth(10 * 10));

		s.setWidth(500);
		s.orderByMe();

		table.add(create.apply(DISPLAY_RESOLUTION));
		table.add(create.apply(CAMERA_RESOLUTION));
		table.add(create.apply(FRONT_CAMERA_RESOLUTION));
		table.add(create.apply(PROZESSOR_TAKT));
		table.add(create.apply(RAM));

		ARankColumnModel c;
		
		c = create.apply(SAR);
		table.add(c);
		c.hide();
		c = create.apply(GEWICHT);
		table.add(c);
		c.hide();
	}
}

