<?xml version="1.0" encoding="UTF-8"?>
<model modelUID="r:013d4b9a-88e7-453e-9aa0-018f5b0d362f(StopWatch.sandbox)">
  <persistence version="8" />
  <language namespace="0cc105df-cb0e-455e-8795-ff832ae504dd(StopWatch)" />
  <language namespace="ceab5195-25ea-4f22-9b92-103b95ca8c0c(jetbrains.mps.lang.core)" />
  <import index="1t7x" modelUID="f:java_stub#6354ebe7-c22a-4a0f-ac54-50b52ab9b065#java.awt(JDK/java.awt@java_stub)" version="-1" />
  <import index="k090" modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="4" implicit="yes" />
  <import index="tpck" modelUID="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" version="0" implicit="yes" />
  <root type="k090.StopWatch" typeId="k090.2155112052277203860" id="7354481749040339843" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test" />
    <node role="ui" roleId="k090.2155112052277249674" type="k090.UI" typeId="k090.2155112052277249665" id="7354481749040339844" nodeInfo="ng">
      <node role="dial" roleId="k090.6422299306367181948" type="k090.Dial" typeId="k090.6422299306367181688" id="7354481749040339845" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="34" />
        <property name="y" nameId="k090.6422299306366969217" value="12" />
        <property name="size" nameId="k090.6422299306366969224" value="34" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="7354481749040339857" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dGREEN" resolveInfo="GREEN" />
        </node>
      </node>
      <node role="startButton" roleId="k090.6422299306366969353" type="k090.Buttons" typeId="k090.2155112052277249666" id="7354481749040339847" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="12" />
        <property name="y" nameId="k090.6422299306366969217" value="44" />
        <property name="size" nameId="k090.6422299306366969224" value="435" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="7354481749040339852" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dCYAN" resolveInfo="CYAN" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.6422299306366969357" type="k090.Buttons" typeId="k090.2155112052277249666" id="7354481749040339849" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="34" />
        <property name="y" nameId="k090.6422299306366969217" value="12" />
        <property name="size" nameId="k090.6422299306366969224" value="23" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="7354481749040339862" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dRED" resolveInfo="RED" />
        </node>
      </node>
    </node>
    <node role="work" roleId="k090.2155112052277249676" type="k090.Time" typeId="k090.2155112052277249667" id="7354481749040339851" nodeInfo="ng" />
  </root>
</model>

