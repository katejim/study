<?xml version="1.0" encoding="UTF-8"?>
<model modelUID="r:013d4b9a-88e7-453e-9aa0-018f5b0d362f(StopWatch.sandbox)">
  <persistence version="8" />
  <language namespace="0cc105df-cb0e-455e-8795-ff832ae504dd(StopWatch)" />
  <language namespace="ceab5195-25ea-4f22-9b92-103b95ca8c0c(jetbrains.mps.lang.core)" />
  <import index="1t7x" modelUID="f:java_stub#6354ebe7-c22a-4a0f-ac54-50b52ab9b065#java.awt(JDK/java.awt@java_stub)" version="-1" />
  <import index="k090" modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="4" implicit="yes" />
  <import index="tpck" modelUID="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" version="0" implicit="yes" />
  <root type="k090.StopWatch" typeId="k090.2155112052277203860" id="6422299306366968103" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test" />
    <node role="ui" roleId="k090.2155112052277249674" type="k090.UI" typeId="k090.2155112052277249665" id="6422299306366968104" nodeInfo="ng">
      <node role="startButton" roleId="k090.6422299306366969353" type="k090.Buttons" typeId="k090.2155112052277249666" id="6422299306366973325" nodeInfo="ng">
        <property name="y" nameId="k090.6422299306366969217" value="23" />
        <property name="ySize" value="435" />
        <property name="x" nameId="k090.6422299306366969191" value="34" />
        <property name="size" nameId="k090.6422299306366969224" value="2342" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367181680" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dBLACK" resolveInfo="BLACK" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.6422299306366969357" type="k090.Buttons" typeId="k090.2155112052277249666" id="6422299306366973329" nodeInfo="ng">
        <property name="type" nameId="k090.6422299306366969349" value="stop" />
        <property name="x" nameId="k090.6422299306366969191" value="12" />
        <property name="y" nameId="k090.6422299306366969217" value="23" />
        <property name="ySize" value="155" />
        <property name="size" nameId="k090.6422299306366969224" value="14" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367181684" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dBLUE" resolveInfo="BLUE" />
        </node>
      </node>
      <node role="dial" roleId="k090.6422299306367181948" type="k090.Dial" typeId="k090.6422299306367181688" id="6422299306367190635" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="34" />
        <property name="y" nameId="k090.6422299306366969217" value="12" />
        <property name="ySize" value="223" />
        <property name="size" nameId="k090.6422299306366969224" value="233" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367190643" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dDARK_GRAY" resolveInfo="DARK_GRAY" />
        </node>
      </node>
    </node>
    <node role="work" roleId="k090.2155112052277249676" type="k090.Time" typeId="k090.2155112052277249667" id="6422299306366968105" nodeInfo="ng" />
  </root>
  <root type="k090.StopWatch" typeId="k090.2155112052277203860" id="6422299306367327469" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Rounded" />
    <node role="ui" roleId="k090.2155112052277249674" type="k090.UI" typeId="k090.2155112052277249665" id="6422299306367327470" nodeInfo="ng">
      <node role="dial" roleId="k090.6422299306367181948" type="k090.Dial" typeId="k090.6422299306367181688" id="6422299306367327471" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="234" />
        <property name="y" nameId="k090.6422299306366969217" value="54" />
        <property name="ySize" value="45" />
        <property name="type" nameId="k090.6422299306367181912" value="round" />
        <property name="size" nameId="k090.6422299306366969224" value="45" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367376616" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dYELLOW" resolveInfo="YELLOW" />
        </node>
      </node>
      <node role="startButton" roleId="k090.6422299306366969353" type="k090.Buttons" typeId="k090.2155112052277249666" id="6422299306367327473" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="12" />
        <property name="y" nameId="k090.6422299306366969217" value="15" />
        <property name="ySize" value="234" />
        <property name="size" nameId="k090.6422299306366969224" value="324" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367376621" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dYELLOW" resolveInfo="YELLOW" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.6422299306366969357" type="k090.Buttons" typeId="k090.2155112052277249666" id="6422299306367327475" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="45" />
        <property name="y" nameId="k090.6422299306366969217" value="123" />
        <property name="ySize" value="34" />
        <property name="size" nameId="k090.6422299306366969224" value="34" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="6422299306367376626" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dGRAY" resolveInfo="GRAY" />
        </node>
      </node>
    </node>
    <node role="work" roleId="k090.2155112052277249676" type="k090.Time" typeId="k090.2155112052277249667" id="6422299306367327477" nodeInfo="ng">
      <property name="precision" nameId="k090.2155112052277249668" value="ms" />
    </node>
  </root>
</model>

