import java.util.*;
/*
class DirectoryManager
{
	Hashtable<StringBuffer, FileInfo> T = new Hashtable<StringBuffer, FileInfo>();
	void enter(StringBuffer key, FileInfo file)
	{
		T.put(key,file);
	}
	FileInfo lookup(StringBuffer key)
	{
		return T.get(key);
	}
}*/


class DirectoryManager
{
	Hashtable<String, FileInfo> T = new Hashtable<String, FileInfo>();
	void enter(String key, FileInfo file)
	{
		T.put(key,file);
	}
	FileInfo lookup(String key)
	{
		return T.get(key);
	}
}