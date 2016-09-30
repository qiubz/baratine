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

package io.baratine.service;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation {@code @OnSave} is used to mark a method of the service responsible
 * for saving service's state to a permanent storage. The storage can be a database
 * or any other persistent medium.
 * <p>
 * When used on a Vault's Asset the {@code @OnSave} method will be called instead
 * of the Vault provided implementation.
 * <p>
 * The method is called when Baratine determines that change of service state had
 * occurred and will time the @OnSave call to its internal checkpoint, i.e.
 * the method may not be necesseraly called on every change, it may be called for accumulated
 * change of state.
 * <p>
 * It is assumed that state changes on a call to any {@code @Modify}
 * marked method.
 *
 * @see io.baratine.service.OnSave
 * @see io.baratine.service.Journal
 * @see io.baratine.service.Modify
 */

@Documented
@Retention(RUNTIME)
@Target({METHOD})
public @interface OnSave
{
}
