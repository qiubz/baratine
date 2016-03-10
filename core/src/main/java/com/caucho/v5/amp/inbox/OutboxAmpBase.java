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

package com.caucho.v5.amp.inbox;

import com.caucho.v5.amp.queue.OutboxContext;
import com.caucho.v5.amp.queue.OutboxDeliverBase;
import com.caucho.v5.amp.spi.InboxAmp;
import com.caucho.v5.amp.spi.MessageAmp;
import com.caucho.v5.amp.spi.OutboxAmpContext;

/**
 * Thread context for ramp events.
 */
public class OutboxAmpBase
  extends OutboxDeliverBase<MessageAmp>
  implements OutboxAmpContext
{
  private InboxAmp _inbox;
  private MessageAmp _message;
  
  private int _openCount;
  
  public OutboxAmpBase()
  {
  }

  @Override
  public InboxAmp inbox()
  {
    return _inbox;
  }
  
  @Override
  public void inbox(InboxAmp inbox)
  {
    /*
    if (inbox == null) {
      Thread.dumpStack();
    }
    */
    
    _inbox = inbox;
  }

  @Override
  public MessageAmp message()
  {
    return _message;
  }
  
  @Override
  public void message(MessageAmp message)
  {
    _message = message;
  }
  
  @Override
  public OutboxContext<MessageAmp> context()
  {
    return _inbox;
  }
  
  @Override
  public OutboxContext<MessageAmp> getAndSetContext(OutboxContext<MessageAmp> context)
  {
    InboxAmp inboxAmp = (InboxAmp) context;
    
    OutboxContext<MessageAmp> oldContext = _inbox;
    
    _inbox = inboxAmp;
    
    if (inboxAmp != null) {
      // _inbox = contextAmp.getInbox();
      _message = inboxAmp.getMessage();
    }
    else {
      _message = null;
      // Thread.dumpStack();
    }
    
    return oldContext;
  }
  
  /*
  @Override
  public OutboxDeliver<MessageAmp> createInit()
  {
    OutboxAmpContextImpl context = new OutboxAmpContextImpl();
    
    context.setInbox(getInbox());
    context.setMessage(getMessage());
    
    return context;
  }
  */

  @Override
  public final void open()
  {
    _openCount++;
  }
  
  @Override
  public final void close()
  {
    if (closeImpl()) {
      super.close();
    }
  }
  
  protected boolean closeImpl()
  {
    return --_openCount <= 0;
  }
  
}
