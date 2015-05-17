package edu.uic.cs478.Shanmathi.Common;

interface MusicPlayer {
   
    void playMusic(long n);
    void stopMusic();
	void pauseMusic();
    void resumeMusic();
    String[] view();
    
}