/*
Copyright (C) 2001, 2010 United States Government
as represented by the Administrator of the
National Aeronautics and Space Administration.
All Rights Reserved.
*/

package gov.nasa.worldwindx.applications.worldwindow.features.swinglayermanager;

import gov.nasa.worldwindx.applications.worldwindow.core.Constants;
import gov.nasa.worldwindx.applications.worldwindow.core.Controller;
import gov.nasa.worldwindx.applications.worldwindow.core.Registry;
import gov.nasa.worldwindx.applications.worldwindow.core.layermanager.ActiveLayersManager;
import gov.nasa.worldwindx.applications.worldwindow.core.layermanager.LayerManager;
import gov.nasa.worldwindx.applications.worldwindow.features.AbstractFeature;
import gov.nasa.worldwindx.applications.worldwindow.features.FeaturePanel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

/**
 * @author tag
 * @version $Id: ControlsPanelImpl.java 1 2011-07-16 23:22:47Z dcollins $
 */
public class ControlsPanelImpl extends AbstractFeature implements ControlsPanel
{
    private static final int DEFAULT_DIVIDER_LOCATION = 250;

    private JPanel panel;

    public ControlsPanelImpl(Registry registry)
    {
        super("Controls Panel", Constants.CONTROLS_PANEL, registry);

        this.panel = new JPanel(new BorderLayout());
    }

    public void initialize(Controller controller)
    {
        super.initialize(controller);

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new BorderLayout());

        LayerManager layerManager = (LayerManager) this.controller.getRegisteredObject(Constants.FEATURE_LAYER_MANAGER);
        if (layerManager != null && layerManager instanceof FeaturePanel)
            centerPanel.add(((FeaturePanel) layerManager).getJPanel(), BorderLayout.CENTER);

        ActiveLayersManager layerList = (ActiveLayersManager) this.controller.getRegisteredObject(
            Constants.FEATURE_ACTIVE_LAYERS_PANEL);
        if (layerList != null && layerList instanceof FeaturePanel)
            topPanel.add(((FeaturePanel) layerList).getJPanel(), BorderLayout.CENTER);

        final JSplitPane splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(DEFAULT_DIVIDER_LOCATION);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);
        
        splitPane.setTopComponent(topPanel);
        splitPane.setBottomComponent(centerPanel);

        this.panel.add(splitPane, BorderLayout.CENTER);
    }

    public JPanel getJPanel()
    {
    	return this.panel;
    }
}
