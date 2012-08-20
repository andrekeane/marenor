/*
 * Copyright (C) 2012 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package gov.nasa.worldwind.symbology.milstd2525;

import junit.framework.TestCase;

/**
 * Unit test for {@link MilStd2525GraphicFactory}.
 *
 * @author pabercrombie
 * @version $Id: MilStd2525TacticalGraphicFactoryTest.java 497 2012-03-29 20:40:07Z pabercrombie $
 */
public class MilStd2525TacticalGraphicFactoryTest
{
    @org.junit.Test
    public void testGraphicSupported() throws IllegalAccessException
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();
        TestCase.assertTrue(factory.isSupported("GFGPGLP----AUSX"));
    }

    @org.junit.Test
    public void testGraphicNotSupported() throws IllegalAccessException
    {
        MilStd2525GraphicFactory factory = new MilStd2525GraphicFactory();
        TestCase.assertFalse(factory.isSupported("GFGPXXX----AUSX")); // Non-existent function ID.
    }
}
