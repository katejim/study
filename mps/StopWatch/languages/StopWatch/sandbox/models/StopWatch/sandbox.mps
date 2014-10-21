<?xml version="1.0" encoding="UTF-8"?>
<model modelUID="r:013d4b9a-88e7-453e-9aa0-018f5b0d362f(StopWatch.sandbox)">
  <persistence version="8" />
  <language namespace="0cc105df-cb0e-455e-8795-ff832ae504dd(StopWatch)" />
  <language namespace="ceab5195-25ea-4f22-9b92-103b95ca8c0c(jetbrains.mps.lang.core)" />
  <import index="1t7x" modelUID="f:java_stub#6354ebe7-c22a-4a0f-ac54-50b52ab9b065#java.awt(JDK/java.awt@java_stub)" version="-1" />
  <import index="k090" modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="6" implicit="yes" />
  <import index="tpck" modelUID="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" version="0" implicit="yes" />
  <root type="k090.StopWatch" typeId="k090.2155112052277203860" id="3271969819419607820" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test2" />
    <node role="ui" roleId="k090.2155112052277249674" type="k090.UI" typeId="k090.2155112052277249665" id="3271969819419607821" nodeInfo="ng">
      <node role="formStopWatch" roleId="k090.6422299306367181948" type="k090.Shape" typeId="k090.6422299306366969172" id="3271969819419607822" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="12" />
        <property name="y" nameId="k090.6422299306366969217" value="134" />
        <property name="size" nameId="k090.6422299306366969224" value="189" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621517" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dBLUE" resolveInfo="BLUE" />
        </node>
      </node>
      <node role="startButton" roleId="k090.6422299306366969353" type="k090.Buttons" typeId="k090.2155112052277249666" id="3271969819419607824" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="234" />
        <property name="y" nameId="k090.6422299306366969217" value="123" />
        <property name="size" nameId="k090.6422299306366969224" value="123" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621521" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dFACTOR" resolveInfo="FACTOR" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.6422299306366969357" type="k090.Buttons" typeId="k090.2155112052277249666" id="3271969819419607826" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="123" />
        <property name="y" nameId="k090.6422299306366969217" value="234" />
        <property name="size" nameId="k090.6422299306366969224" value="129" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621525" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dGREEN" resolveInfo="GREEN" />
        </node>
      </node>
    </node>
    <node role="work" roleId="k090.2155112052277249676" type="k090.Time" typeId="k090.2155112052277249667" id="3271969819419607828" nodeInfo="ng" />
  </root>
  <root type="k090.StopWatch" typeId="k090.2155112052277203860" id="3271969819419621795" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test1" />
    <node role="ui" roleId="k090.2155112052277249674" type="k090.UI" typeId="k090.2155112052277249665" id="3271969819419621796" nodeInfo="ng">
      <node role="formStopWatch" roleId="k090.6422299306367181948" type="k090.Shape" typeId="k090.6422299306366969172" id="3271969819419621797" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="123" />
        <property name="y" nameId="k090.6422299306366969217" value="1231" />
        <property name="size" nameId="k090.6422299306366969224" value="231" />
        <property name="formType" nameId="k090.3271969819419415983" value="squared" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621804" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dGRAY" resolveInfo="GRAY" />
        </node>
      </node>
      <node role="startButton" roleId="k090.6422299306366969353" type="k090.Buttons" typeId="k090.2155112052277249666" id="3271969819419621799" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="123" />
        <property name="y" nameId="k090.6422299306366969217" value="89" />
        <property name="size" nameId="k090.6422299306366969224" value="1234" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621807" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dGREEN" resolveInfo="GREEN" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.6422299306366969357" type="k090.Buttons" typeId="k090.2155112052277249666" id="3271969819419621801" nodeInfo="ng">
        <property name="x" nameId="k090.6422299306366969191" value="123" />
        <property name="y" nameId="k090.6422299306366969217" value="231" />
        <property name="size" nameId="k090.6422299306366969224" value="1231" />
        <node role="color" roleId="k090.6422299306367173923" type="k090.ColorReference" typeId="k090.6422299306366979353" id="3271969819419621810" nodeInfo="ng">
          <link role="color" roleId="k090.6422299306366979354" targetNodeId="1t7x.~Color%dCYAN" resolveInfo="CYAN" />
        </node>
      </node>
    </node>
    <node role="work" roleId="k090.2155112052277249676" type="k090.Time" typeId="k090.2155112052277249667" id="3271969819419621803" nodeInfo="ng" />
  </root>
</model>

