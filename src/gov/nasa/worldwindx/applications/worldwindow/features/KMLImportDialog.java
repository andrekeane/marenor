package gov.nasa.worldwindx.applications.worldwindow.features;

import gov.nasa.worldwind.exception.NoItemException;
import gov.nasa.worldwind.exception.ServiceException;
import gov.nasa.worldwind.poi.PointOfInterest;

import java.util.List;

/**
 * Interface for KML Import
 *
 * @author keane
 * @version $Id: KMLImport.java 1 2011-07-16 23:22:47Z keane $
 */

public interface KMLImportDialog {
	
	public List<PointOfInterest> findPlaces(String placeInfo) throws NoItemException, ServiceException;
	
}
