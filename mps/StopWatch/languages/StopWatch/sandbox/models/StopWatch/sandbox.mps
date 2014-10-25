<?xml version="1.0" encoding="UTF-8"?>
<model modelUID="r:013d4b9a-88e7-453e-9aa0-018f5b0d362f(StopWatch.sandbox)">
  <persistence version="8" />
  <language namespace="0cc105df-cb0e-455e-8795-ff832ae504dd(StopWatch)" />
  <language namespace="ceab5195-25ea-4f22-9b92-103b95ca8c0c(jetbrains.mps.lang.core)" />
  <import index="1t7x" modelUID="f:java_stub#6354ebe7-c22a-4a0f-ac54-50b52ab9b065#java.awt(JDK/java.awt@java_stub)" version="-1" />
  <import index="k090" modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="9" implicit="yes" />
  <import index="tpck" modelUID="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" version="0" implicit="yes" />
  <root type="k090.Main" typeId="k090.7942873407391151944" id="7942873407393069486" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test1" />
    <node role="stopwatch" roleId="k090.7942873407391152038" type="k090.Analog" typeId="k090.7942873407391478165" id="7942873407393069491" nodeInfo="ng">
      <property name="weight" nameId="k090.7942873407391152063" value="12" />
      <property name="form" nameId="k090.7942873407391152064" value="squared" />
      <node role="button" roleId="k090.7942873407391596002" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393069496" nodeInfo="ng">
        <property name="form" nameId="k090.7942873407391595994" value="squared" />
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069501" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="122" />
          <property name="leftY" nameId="k090.7942873407391687282" value="232" />
          <property name="size" nameId="k090.7942873407391687289" value="123" />
        </node>
      </node>
      <node role="dial" roleId="k090.7942873407391939635" type="k090.Dial" typeId="k090.7942873407391687212" id="7942873407393069506" nodeInfo="ng">
        <property name="fontSize" nameId="k090.7942873407393069627" value="234" />
        <node role="place" roleId="k090.7942873407391921963" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069511" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="2" />
          <property name="leftY" nameId="k090.7942873407391687282" value="3" />
          <property name="size" nameId="k090.7942873407391687289" value="12" />
        </node>
      </node>
      <node role="dimensions" roleId="k090.7942873407391152079" type="k090.Dimensions" typeId="k090.7942873407391088954" id="7942873407393069516" nodeInfo="ng">
        <property name="height" nameId="k090.7942873407391088984" value="12" />
        <property name="width" nameId="k090.7942873407391088986" value="231" />
        <property name="depth" nameId="k090.7942873407391088989" value="1" />
      </node>
    </node>
  </root>
  <root type="k090.Main" typeId="k090.7942873407391151944" id="7942873407393069545" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test2" />
    <node role="stopwatch" roleId="k090.7942873407391152038" type="k090.Digital" typeId="k090.7942873407391478170" id="7942873407393069550" nodeInfo="ng">
      <property name="weight" nameId="k090.7942873407391152063" value="124" />
      <node role="timePrecision" roleId="k090.7942873407391562643" type="k090.Time" typeId="k090.2155112052277249667" id="7942873407393069555" nodeInfo="ng">
        <property name="value" nameId="k090.7942873407391562636" value="34" />
        <property name="precision" nameId="k090.2155112052277249668" value="ms" />
      </node>
      <node role="startButton" roleId="k090.7942873407391617888" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393069560" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069565" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="2" />
          <property name="leftY" nameId="k090.7942873407391687282" value="3" />
          <property name="size" nameId="k090.7942873407391687289" value="1" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.7942873407391655464" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393069570" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069575" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="1" />
          <property name="leftY" nameId="k090.7942873407391687282" value="2" />
          <property name="size" nameId="k090.7942873407391687289" value="4" />
        </node>
      </node>
      <node role="pauseButton" roleId="k090.7942873407391655474" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393069580" nodeInfo="ng">
        <property name="form" nameId="k090.7942873407391595994" value="squared" />
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069585" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="1" />
          <property name="leftY" nameId="k090.7942873407391687282" value="4" />
          <property name="size" nameId="k090.7942873407391687289" value="2" />
        </node>
      </node>
      <node role="dial" roleId="k090.7942873407391939635" type="k090.Dial" typeId="k090.7942873407391687212" id="7942873407393069590" nodeInfo="ng">
        <property name="form" nameId="k090.7942873407391687213" value="squared" />
        <property name="fontSize" nameId="k090.7942873407393069627" value="12" />
        <node role="place" roleId="k090.7942873407391921963" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393069595" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="123" />
          <property name="leftY" nameId="k090.7942873407391687282" value="231" />
          <property name="size" nameId="k090.7942873407391687289" value="1" />
        </node>
      </node>
      <node role="dimensions" roleId="k090.7942873407391152079" type="k090.Dimensions" typeId="k090.7942873407391088954" id="7942873407393069600" nodeInfo="ng">
        <property name="height" nameId="k090.7942873407391088984" value="122" />
        <property name="width" nameId="k090.7942873407391088986" value="323" />
        <property name="depth" nameId="k090.7942873407391088989" value="12" />
      </node>
      <node role="specialButtons" roleId="k090.7942873407391655487" type="k090.LightButton" typeId="k090.7942873407391665334" id="7942873407393098801" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393098802" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="1" />
          <property name="leftY" nameId="k090.7942873407391687282" value="2" />
          <property name="size" nameId="k090.7942873407391687289" value="3" />
        </node>
      </node>
    </node>
  </root>
  <root type="k090.Main" typeId="k090.7942873407391151944" id="7942873407393127684" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Test3" />
    <node role="stopwatch" roleId="k090.7942873407391152038" type="k090.Digital" typeId="k090.7942873407391478170" id="7942873407393127793" nodeInfo="ng">
      <property name="weight" nameId="k090.7942873407391152063" value="122" />
      <property name="form" nameId="k090.7942873407391152064" value="squared" />
      <node role="timePrecision" roleId="k090.7942873407391562643" type="k090.Time" typeId="k090.2155112052277249667" id="7942873407393127798" nodeInfo="ng">
        <property name="value" nameId="k090.7942873407391562636" value="100" />
        <property name="precision" nameId="k090.2155112052277249668" value="ms" />
      </node>
      <node role="startButton" roleId="k090.7942873407391617888" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393127803" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393127808" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="12" />
          <property name="leftY" nameId="k090.7942873407391687282" value="12" />
          <property name="size" nameId="k090.7942873407391687289" value="12" />
        </node>
      </node>
      <node role="stopButton" roleId="k090.7942873407391655464" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393127813" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393127818" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="15" />
          <property name="leftY" nameId="k090.7942873407391687282" value="15" />
          <property name="size" nameId="k090.7942873407391687289" value="15" />
        </node>
      </node>
      <node role="pauseButton" roleId="k090.7942873407391655474" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393127823" nodeInfo="ng">
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393127828" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="11" />
          <property name="leftY" nameId="k090.7942873407391687282" value="11" />
          <property name="size" nameId="k090.7942873407391687289" value="11" />
        </node>
      </node>
      <node role="dial" roleId="k090.7942873407391939635" type="k090.Dial" typeId="k090.7942873407391687212" id="7942873407393127833" nodeInfo="ng">
        <property name="fontSize" nameId="k090.7942873407393069627" value="15" />
        <node role="place" roleId="k090.7942873407391921963" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393127838" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="12" />
          <property name="leftY" nameId="k090.7942873407391687282" value="234" />
          <property name="size" nameId="k090.7942873407391687289" value="123" />
        </node>
      </node>
      <node role="dimensions" roleId="k090.7942873407391152079" type="k090.Dimensions" typeId="k090.7942873407391088954" id="7942873407393127843" nodeInfo="ng">
        <property name="depth" nameId="k090.7942873407391088989" value="2" />
        <property name="height" nameId="k090.7942873407391088984" value="23" />
        <property name="width" nameId="k090.7942873407391088986" value="122" />
      </node>
      <node role="specialButtons" roleId="k090.7942873407391655487" type="k090.ShowCurrentType" typeId="k090.7942873407391608492" id="7942873407393127870" nodeInfo="ng">
        <property name="showTime" nameId="k090.7942873407391608493" value="true" />
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393127871" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="18" />
          <property name="leftY" nameId="k090.7942873407391687282" value="18" />
          <property name="size" nameId="k090.7942873407391687289" value="18" />
        </node>
      </node>
      <node role="specialButtons" roleId="k090.7942873407391655487" type="k090.Memorization" typeId="k090.7942873407393012337" id="7942873407393217142" nodeInfo="ng">
        <property name="memorization" nameId="k090.7942873407393156784" value="true" />
        <node role="button" roleId="k090.7942873407393012389" type="k090.Button" typeId="k090.7942873407391595904" id="7942873407393217145" nodeInfo="ng">
          <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393217148" nodeInfo="ng" />
        </node>
        <node role="place" roleId="k090.7942873407391637133" type="k090.Coordinates" typeId="k090.7942873407391687224" id="7942873407393217151" nodeInfo="ng">
          <property name="leftX" nameId="k090.7942873407391687278" value="23" />
          <property name="leftY" nameId="k090.7942873407391687282" value="23" />
          <property name="size" nameId="k090.7942873407391687289" value="23" />
        </node>
      </node>
    </node>
  </root>
</model>

