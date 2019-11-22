package model.enumeration;

public enum GameStatus 
{
	ADDPLAYERS
	{
		@Override
		public String statusString() 
		{
			return "Add a new player";
		}
	},
	READY
	{
		@Override
		public String statusString() 
		{
			return "Ready to spin!";
		}
	},
	INPROGRESS
	{
		@Override
		public String statusString() 
		{
			return "Spin in progress...";
		}
	},
	WAITING
	{
		@Override
		public String statusString()
		{
			return "Waiting for others spin";
		}
	},
	SPINNERSPINNING
	{
		@Override
		public String statusString()
		{
			return "All players have spinned, spinner spinning now!!";
		}
	},
	BEFOREBET
	{

		@Override
		public String statusString() 
		{
			return "Please place a bet to start spin";
		}
	};
	public abstract String statusString();
}