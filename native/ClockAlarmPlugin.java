package com.rotina.almoco;

import android.content.Intent;
import android.provider.AlarmClock;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "ClockAlarm")
public class ClockAlarmPlugin extends Plugin {
  @PluginMethod
  public void setAlarm(PluginCall call) {
    Integer hour = call.getInt("hour");
    Integer minute = call.getInt("minute");
    String msg = call.getString("message", "Alarme");
    if (hour == null || minute == null) { call.reject("Horario invalido"); return; }
    try {
      Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
      i.putExtra(AlarmClock.EXTRA_HOUR, hour);
      i.putExtra(AlarmClock.EXTRA_MINUTES, minute);
      i.putExtra(AlarmClock.EXTRA_MESSAGE, msg);
      i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      getContext().startActivity(i);
      call.resolve();
    } catch (Exception e) { call.reject(e.getMessage()); }
  }

  @PluginMethod
  public void openAlarms(PluginCall call) {
    try {
      Intent i = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
      i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      getContext().startActivity(i);
      call.resolve();
    } catch (Exception e) { call.reject(e.getMessage()); }
  }
}
