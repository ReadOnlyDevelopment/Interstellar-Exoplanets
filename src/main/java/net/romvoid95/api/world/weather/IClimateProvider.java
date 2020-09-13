package net.romvoid95.api.world.weather;

public interface IClimateProvider
{
	public ICloudProvider getCloudProvider();

	public IStormProvider getStormProvider();
}