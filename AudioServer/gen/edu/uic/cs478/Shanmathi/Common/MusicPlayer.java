/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /Users/shanmathimayuramkrithivasan/Downloads/coursera-android-master/Examples/ServiceWithIPCExampleService/src/edu/uic/cs478/Shanmathi/Common/MusicPlayer.aidl
 */
package edu.uic.cs478.Shanmathi.Common;
public interface MusicPlayer extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements edu.uic.cs478.Shanmathi.Common.MusicPlayer
{
private static final java.lang.String DESCRIPTOR = "edu.uic.cs478.Shanmathi.Common.MusicPlayer";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an edu.uic.cs478.Shanmathi.Common.MusicPlayer interface,
 * generating a proxy if needed.
 */
public static edu.uic.cs478.Shanmathi.Common.MusicPlayer asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof edu.uic.cs478.Shanmathi.Common.MusicPlayer))) {
return ((edu.uic.cs478.Shanmathi.Common.MusicPlayer)iin);
}
return new edu.uic.cs478.Shanmathi.Common.MusicPlayer.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_playMusic:
{
data.enforceInterface(DESCRIPTOR);
long _arg0;
_arg0 = data.readLong();
this.playMusic(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_stopMusic:
{
data.enforceInterface(DESCRIPTOR);
this.stopMusic();
reply.writeNoException();
return true;
}
case TRANSACTION_pauseMusic:
{
data.enforceInterface(DESCRIPTOR);
this.pauseMusic();
reply.writeNoException();
return true;
}
case TRANSACTION_resumeMusic:
{
data.enforceInterface(DESCRIPTOR);
this.resumeMusic();
reply.writeNoException();
return true;
}
case TRANSACTION_view:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String[] _result = this.view();
reply.writeNoException();
reply.writeStringArray(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements edu.uic.cs478.Shanmathi.Common.MusicPlayer
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void playMusic(long n) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeLong(n);
mRemote.transact(Stub.TRANSACTION_playMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void stopMusic() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_stopMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void pauseMusic() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_pauseMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void resumeMusic() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_resumeMusic, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String[] view() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_view, _data, _reply, 0);
_reply.readException();
_result = _reply.createStringArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_playMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_stopMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_pauseMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_resumeMusic = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_view = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
}
public void playMusic(long n) throws android.os.RemoteException;
public void stopMusic() throws android.os.RemoteException;
public void pauseMusic() throws android.os.RemoteException;
public void resumeMusic() throws android.os.RemoteException;
public java.lang.String[] view() throws android.os.RemoteException;
}
