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
 *   Free Software Foundation, Inc.
 *   59 Temple Place, Suite 330
 *   Boston, MA 02111-1307  USA
 *
 * @author Scott Ferguson
 */

package com.caucho.v5.util;

import java.util.ListIterator;

/**
 * A null iterator
 */
public class NullIterator<E> implements ListIterator<E> {
  private static final NullIterator NULL = new NullIterator();

  public static <E> NullIterator<E> create()
  {
    return NULL;
  }

  public boolean hasNext()
  {
    return false;
  }

  public E next()
  {
    return null;
  }

  public int nextIndex()
  {
    return -1;
  }

  public boolean hasPrevious()
  {
    return false;
  }

  public E previous()
  {
    return null;
  }

  public int previousIndex()
  {
    return -1;
  }

  public void add(E o)
  {
    throw new UnsupportedOperationException();
  }

  public void set(E o)
  {
    throw new UnsupportedOperationException();
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}
