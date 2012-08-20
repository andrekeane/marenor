/*
 * Copyright (C) 2011 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package gov.nasa.worldwind.ogc.kml.gx;

import gov.nasa.worldwind.ogc.kml.KMLUpdate;

/**
 * @author tag
 * @version $Id: GXAnimatedUpdate.java 1 2011-07-16 23:22:47Z dcollins $
 */
public class GXAnimatedUpdate extends GXAbstractTourPrimitive
{
    public GXAnimatedUpdate(String namespaceURI)
    {
        super(namespaceURI);
    }

    public Double getDuration()
    {
        return (Double) this.getField("duration");
    }

    public KMLUpdate getUpdate()
    {
        return (KMLUpdate) this.getField("Update");
    }
}