/*
 * Copyright (c) 1998-2015 Caucho Technology -- all rights reserved
 *
 * This file is part of Baratine(TM)
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

package com.caucho.v5.web.webapp;

import io.baratine.web.RequestWeb;
import io.baratine.web.ServiceWeb;


/**
 * Wrapper for filter requests.
 */
public class RequestFilter extends RequestWrapper
{
  private RequestWeb _delegate;
  
  private ServiceWeb []_services;
  private int _index;
  
  RequestFilter(RequestWeb delegate, ServiceWeb []services)
  {
    _delegate = delegate;
    _services = services;
  }

  @Override
  protected RequestWeb delegate() 
  { 
    return _delegate;
  }
  
  @Override
  public void ok()
  {
    try {
      if (_index < _services.length) {
        _services[_index++].handle(this);
      }
      else {
        delegate().ok();
      }
    } catch (Throwable e) {
      _delegate.fail(e);
    }
  }
  
  @Override
  public void ok(Object value)
  {
    delegate().ok(value);
  }
}
