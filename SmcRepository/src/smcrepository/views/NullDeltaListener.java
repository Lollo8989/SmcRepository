package smcrepository.views;

import java.io.Serializable;

public class NullDeltaListener implements IDeltaListener,Serializable {
	protected static NullDeltaListener soleInstance = new NullDeltaListener();

	public static NullDeltaListener getSoleInstance() {
		return soleInstance;
	}

	/*
	 * @see IDeltaListener#add(DeltaEvent)
	 */
	public void add(DeltaEvent event) {
	}

	/*
	 * @see IDeltaListener#remove(DeltaEvent)
	 */
	public void remove(DeltaEvent event) {
	}

}
