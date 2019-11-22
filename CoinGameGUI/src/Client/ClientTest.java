package Client;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.AppFrame;
import view.GameEngineCallbackImpl;

public class ClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			GameEngine gameEngine=new GameEngineImpl();
			new AppFrame(gameEngine);
			gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
	}

}
