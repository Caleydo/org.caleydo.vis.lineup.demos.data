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
	private static int TELEFON;
	private static int INTERNET;
	private static int MULTIMEDIA;
	private static int HANDLING;
	private static int APP_STORE;
	private static int APP_STORE_A;
	private static int KERNE;
	private static int RAM;
	private static int SAR;
	private static int AKKU;
	private static int GEWICHT;
	private static int DISPLAY;
	private static int PPI;
	private static int SD;
	private static int CAMERA;
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
			Files.copy(ChipView.class.getResourceAsStream("chip_2014_09.txt"), temp,
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
		int i = 0, j;
		Deque<Color> f = new ArrayDeque<>(ColorBrewer.Set3.get(12));
		f.addAll(ColorBrewer.Set2.get(8));
		// Produkt
		columns.add(cstring(PRODUCT = i++));
		// Preis
		columns.add(cdouble(PREIS = i++, 0, Double.NaN).setColor(f.pollFirst(), null));
		// Gesamtwertung
		columns.add(cdouble(GESAMT = i++, 0, 100).setColor(f.pollFirst(), null));
		// Preis-Leistung
		columns.add(cdouble(PREIS_LEISTUNG = i++, 0, 100).setColor(f.pollFirst(), null));
		// Telefon und Akku
		columns.add(cdouble(TELEFON = i++, 0, 100).setColor(f.pollFirst(), null));
		// Internet
		columns.add(cdouble(INTERNET = i++, 0, 100).setColor(f.pollFirst(), null));
		// Multimedia
		columns.add(cdouble(MULTIMEDIA = i++, 0, 100).setColor(f.pollFirst(), null));
		// Handling
		columns.add(cdouble(HANDLING = i++, 0, 100).setColor(f.pollFirst(), null));
		// App-Store
		columns.add(cdouble(APP_STORE = i++, 0, 100).setColor(f.pollFirst(), null));
		j = APP_STORE + 1;
		// Getestet mit
		i++;
		// Aktuelles Betriebssystem
		i++;
		// Handbuch
		i++;
		// App-Store-Anbindung;Prozessor
		APP_STORE_A = j++;
		columns.add(category(i++, "Apple App Store", "BlackBerry App World", "Google Play", "Ovi Store",
				"Samsung Apps", "Windows Phone Store").setColor(f.pollFirst(), null));
		// Prozessor
		i++;
		// Prozessor-Kerne
		KERNE = j++;
		columns.add(cdouble(i++, 1, 8).setColor(f.pollFirst(), null));
		// Prozessor-Takt (MHz)
		i++;
		// Arbeitsspeicher (MByte)
		RAM = j++;
		columns.add(cdouble(i++, 0, 3072).setColor(f.pollFirst(), null));
		// SAR-Wert (W/kg)
		SAR = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Akku Sprechzeit (Stunden)
		i++;
		// Akku Online-Laufzeit (Stunden)
		AKKU = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Akku Lade-Dauer (Stunden)
		i++;
		// Akku: Kapazität (mAh)
		i++;
		// Akku: Austauschbar
		i++;
		// Gesamt-Wertung Akku
		i++;
		// Gewicht (g)
		GEWICHT = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Höhe x Breite (mm)
		i++;
		// Dicke (mm)
		i++;
		// Anmutung
		i++;
		// WLAN
		i++;
		// GSM-Frequenzbänder
		i++;
		// EDGE
		i++;
		// UMTS: Daten empfangen
		i++;
		// UMTS: Daten versenden
		i++;
		// LTE: Unterstützte Frequenzbänder
		i++;
		// LTE: Cat-4
		i++;
		// Videotelefon
		i++;
		// Touchscreen
		i++;
		// Touchscreen: Reaktionsfreudigkeit
		i++;
		// Display: Typ
		i++;
		// Display: Abmessungen
		i++;
		// Display: Diagonale (Zoll)
		i++;
		// Display: Auflösung
		DISPLAY = j++;
		columns.add(category(i++, "1080 x 1920", "720 x 1280", "1440 x 2560", "640 x 1136", "768 x 1280",
				"540 x 960",
				"480 x 800", "480 x 854", "640 x 960", "768 x 1024", "320 x 480", "720 x 720", "450 x 854", "240 x 320")
				.setColor(f.pollFirst(), null));
		// Display: Pixeldichte (ppi)
		PPI = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Display: Farbanzahl
		i++;
		// Display: Reflektions-Kontrast
		i++;
		// Display: Helligkeit bei Umgebungslicht (cd/m²)
		i++;
		// Display: Schachbrett-Kontrast
		i++;
		// Sprachaufnahme
		i++;
		// Profile
		i++;
		// Push-E-Mail
		i++;
		// E-Mail per Exchange
		i++;
		// Speicher (MByte)
		SD = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Speicherkarten-Slot
		i++;
		// USB-Version
		i++;
		// USB-Buchse
		i++;
		// USB: Zeit für Übertragung von 100 MByte (s)
		i++;
		// SIM-Slot
		i++;
		// Bluetooth
		i++;
		// NFC
		i++;
		// Kamera: Auflösung (Megapixel)
		CAMERA = j++;
		columns.add(cdouble(i++, Double.NaN, Double.NaN).setColor(f.pollFirst(), null));
		// Front-Kamera: Auflösung
		i++;
		// Kamera: Autofokus
		i++;
		// Kamera: Blitztyp
		i++;
		// Camcorder-Auflösung
		i++;
		// TV-Ausgang
		i++;
		// Musik-Player: Formate
		i++;
		// Video-Player
		i++;
		// UKW-Radio
		i++;
		// Navi: GPS-Empänger
		i++;
		// Navi: Software
		i++;
		// Navi: Vollversion
		i++;
		// Navi: Software-Typ
		i++;
		// Navi: Landkarten
		i++;
		// Navi: GPS-Fix
		i++;
		// Benchmark: Browsermark 1.0
		i++;
		// Benchmark: Browsermark 2.0
		i++;
		// Getestet am
		i++;

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

		table.add(create.apply(PRODUCT));
		table.add(create.apply(PREIS));
		table.add(create.apply(PREIS_LEISTUNG));

		StackedRankColumnModel s = new StackedRankColumnModel();
		table.add(s);

		s.add(create.apply(TELEFON).setWidth(20 * 10));
		s.add(create.apply(INTERNET).setWidth(20 * 10));
		s.add(create.apply(MULTIMEDIA).setWidth(20 * 10));
		s.add(create.apply(HANDLING).setWidth(30 * 10));
		s.add(create.apply(APP_STORE).setWidth(10 * 10));

		s.setWidth(300);
		s.orderByMe();

		table.add(create.apply(DISPLAY));
		table.add(create.apply(KERNE));
		table.add(create.apply(RAM));
		table.add(create.apply(AKKU));
		table.add(create.apply(CAMERA));

		ARankColumnModel c;
		c = create.apply(APP_STORE_A);
		table.add(c);
		c.hide();

		c = create.apply(SAR);
		table.add(c);
		c.hide();
		c = create.apply(GEWICHT);
		table.add(c);
		c.hide();
		c = create.apply(PPI);
		table.add(c);
		c.hide();
		c = create.apply(SD);
		table.add(c);
		c.hide();
	}
}

