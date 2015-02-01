package com.ufufund.mvc;

import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

public class UfuVelocityViewResolver extends VelocityViewResolver{
	/**
	 * Requires {@link UfuVelocityView}.
	 */
	@Override
	protected Class<UfuVelocityView> requiredViewClass() {
		return UfuVelocityView.class;
	}
}
