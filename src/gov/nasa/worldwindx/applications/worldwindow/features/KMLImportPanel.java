/*
Copyright (C) 2001, 2010 United States Government
as represented by the Administrator of the
National Aeronautics and Space Administration.
All Rights Reserved.

 */
package gov.nasa.worldwindx.applications.worldwindow.features;

import gov.nasa.worldwind.exception.NoItemException;
import gov.nasa.worldwind.poi.PointOfInterest;
import gov.nasa.worldwindx.applications.worldwindow.core.Constants;
import gov.nasa.worldwindx.applications.worldwindow.core.Controller;
import gov.nasa.worldwindx.applications.worldwindow.core.ImageLibrary;
import gov.nasa.worldwindx.applications.worldwindow.core.Registry;
import gov.nasa.worldwindx.applications.worldwindow.util.Util;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * Panel for KML Import
 *
 * @author keane
 * @version $Id: KMLImportPanel.java 1 2011-07-16 23:22:47Z keane $
 */
public class KMLImportPanel extends AbstractFeature implements FeaturePanel
{
    private JPanel panel;
    private KMLImportDialog kmlimport;

    public KMLImportPanel (Registry registry)
    {
        super("KML Import", Constants.FEATURE_KML_IMPORT, registry);

        this.panel = new JPanel(new BorderLayout());
    }

    public void initialize(final Controller controller)
    {
        super.initialize(controller);

        this.kmlimport= getKMLImport();

        this.panel.setOpaque(false);
        createComponents(this.panel);
    }

    public JPanel getJPanel()
    {
        return this.panel;
    }

    public JComponent[] getDialogControls()
    {
        return null;
    }

    private KMLImportDialog getKMLImport()
    {
        if (this.kmlimport != null)
            return this.kmlimport;

        Object o = controller.getRegisteredObject(Constants.FEATURE_KML_IMPORT);

        return o instanceof KMLImportDialog ? (KMLImportDialog) o : null;
    }

    private void createComponents(JPanel p)
    {
        String tt = "Path to KML file.";

        JComboBox field = new JComboBox();
        field.setOpaque(false);
        field.setAlignmentY(1);
        field.setEditable(true);
        field.setLightWeightPopupEnabled(false);
        field.setPreferredSize(new Dimension(200, field.getPreferredSize().height));
        field.setToolTipText(tt);

        JLabel label = new JLabel(
            ImageLibrary.getIcon("gov/nasa/worldwindx/applications/worldwindow/images/wms-64x64.png"));
//            new ImageIcon(getClass().getResource("gov/nasa/worldwindx/applications/worldwindow/images/safari-24x24.png")));
        label.setOpaque(false);
        label.setToolTipText(tt);

        p.add(label, BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);

        field.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                performKMLAction(actionEvent);
            }
        });
    }

    private void performKMLAction(final ActionEvent e)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    handleEntryAction(e);
                }
                catch (NoItemException e)
                {
                    controller.showMessageDialog("No search string was specified", "No Search String",
                        JOptionPane.ERROR_MESSAGE);
                }
                catch (Exception e)
                {
                    controller.showMessageDialog("Location not found", "Location Unknown", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void handleEntryAction(ActionEvent actionEvent) throws IOException, ParserConfigurationException,
        XPathExpressionException, SAXException, NoItemException
    {
        if (this.getKMLImport() == null)
        {
            Util.getLogger().severe("No kmlimport is registered");
            return;
        }

        String lookupString;

        JComboBox cmb = ((JComboBox) actionEvent.getSource());
        lookupString = cmb.getSelectedItem().toString();

        if (lookupString == null || lookupString.length() < 1)
            return;

        java.util.List<PointOfInterest> results = this.kmlimport.findPlaces(lookupString);
        if (results == null || results.size() == 0)
            return;

        this.controller.moveToLocation(results.get(0));

        // Add it to the list if not already there
        for (int i = 0; i < cmb.getItemCount(); i++)
        {
            Object oi = cmb.getItemAt(i);
            if (oi != null && oi.toString().trim().equals(lookupString))
                return; // item exists
        }
        cmb.insertItemAt(lookupString, 0);
    }
}
