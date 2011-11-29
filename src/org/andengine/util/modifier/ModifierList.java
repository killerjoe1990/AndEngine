package org.andengine.util.modifier;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.util.SmartList;

/**
 * (c) 2010 Nicolas Gramlich 
 * (c) 2011 Zynga Inc.
 * 
 * @author Nicolas Gramlich
 * @since 14:34:57 - 03.09.2010
 */
public class ModifierList<T> extends SmartList<IModifier<T>> implements IUpdateHandler {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final long serialVersionUID = 1610345592534873475L;

	// ===========================================================
	// Fields
	// ===========================================================

	private final T mTarget;

	// ===========================================================
	// Constructors
	// ===========================================================

	public ModifierList(final T pTarget) {
		this.mTarget = pTarget;
	}

	public ModifierList(final T pTarget, final int pCapacity){
		super(pCapacity);
		this.mTarget = pTarget;
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public T getTarget() {
		return this.mTarget;
	}

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================

	@Override
	public void onUpdate(final float pSecondsElapsed) {
		final int modifierCount = this.size();
		if(modifierCount > 0) {
			for(int i = modifierCount - 1; i >= 0; i--) {
				final IModifier<T> modifier = this.get(i);
				modifier.onUpdate(pSecondsElapsed, this.mTarget);
				if(modifier.isFinished() && modifier.isRemoveWhenFinished()) {
					this.remove(i);
				}
			}
		}
	}

	@Override
	public void reset() {
		for(int i = this.size() - 1; i >= 0; i--) {
			this.get(i).reset();
		}
	}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
