/*
 * Hello World - Contract
 * Copyright (c) 2021 ACME Ltd. <contact@acme.com>
 */

package com.acme.helloworld.contract.messages.notifications;

import com.acme.helloworld.contract.data.WindowBounds;

public record WindowBoundsChangedNotification(WindowBounds bounds) {}
