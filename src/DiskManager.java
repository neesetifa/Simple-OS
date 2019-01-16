class DiskManager{
	int get_next_free_sector(int num)
	{
		return mainClass.disks[num].next_free_sector;
	}

	//just for test
	int get_next()
	{
		//return mainClass.disk.next_free_sector();
		return 0;
	}

	


}