package net.romvoid95.api.world;

public interface IClimateProvider
{
	public ICloudProvider getCloudProvider();

	public IStormProvider getStormProvider();
}