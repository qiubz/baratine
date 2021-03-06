/*
 * Copyright (c) 1998-2015 Caucho Technology -- all rights reserved
 *
 * This file is part of Baratine(TM)(TM)
 *
 * Each copy or derived work must preserve the copyright notice and this
 * notice unmodified.
 *
 * Baratine is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * Baratine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE, or any warranty
 * of NON-INFRINGEMENT.  See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Baratine; if not, write to the
 *
 *   Free Software Foundation, Inc.
 *   59 Temple Place, Suite 330
 *   Boston, MA 02111-1307  USA
 *
 * @author Scott Ferguson
 */

package com.caucho.v5.i18n;

import com.caucho.v5.config.ConfigException;
import com.caucho.v5.io.i18n.Encoding;
import com.caucho.v5.loader.EnvironmentLocal;
import com.caucho.v5.util.L10N;

import javax.annotation.PostConstruct;

/**
 * Configuration for the character-encoding pattern.
 */
public class CharacterEncoding {
  private static final L10N L = new L10N(CharacterEncoding.class);

  private static final EnvironmentLocal<String> _localEncoding
    = new EnvironmentLocal<String>();

  private static final String _systemEncoding;

  private String _encoding;

  /**
   * Sets the name
   */
  public void setValue(String name)
  {
    _encoding = Encoding.getMimeName(name);
  }

  public static String getLocalEncoding()
  {
    String encoding = _localEncoding.get();

    if (encoding != null)
      return encoding;
    else
      return _systemEncoding;
  }

  /**
   * Initialize the resource.
   */
  @PostConstruct
  public void init()
    throws Exception
  {
    if (_encoding == null)
      throw new ConfigException(L.l("character-encoding requires a 'value' attribute with the character encoding."));

    _localEncoding.set(_encoding);
  }

  public String toString()
  {
    return getClass().getSimpleName() + "[" + _encoding + "]";
  }

  static {
    String encoding = System.getProperty("file.encoding");

    if (encoding != null)
      encoding = Encoding.getMimeName(encoding);
    else
      encoding = "utf-8";

    _systemEncoding = encoding;
  }
}

