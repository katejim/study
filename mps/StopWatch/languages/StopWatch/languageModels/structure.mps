<?xml version="1.0" encoding="UTF-8"?>
<model modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="9">
  <persistence version="8" />
  <language namespace="c72da2b9-7cce-4447-8389-f407dc1158b7(jetbrains.mps.lang.structure)" />
  <devkit namespace="fbc25dd2-5da4-483a-8b19-70928e1b62d7(jetbrains.mps.devkit.general-purpose)" />
  <import index="tpee" modelUID="r:00000000-0000-4000-0000-011c895902ca(jetbrains.mps.baseLanguage.structure)" version="5" />
  <import index="tpce" modelUID="r:00000000-0000-4000-0000-011c89590292(jetbrains.mps.lang.structure.structure)" version="0" implicit="yes" />
  <import index="tpck" modelUID="r:00000000-0000-4000-0000-011c89590288(jetbrains.mps.lang.core.structure)" version="0" implicit="yes" />
  <import index="k090" modelUID="r:36343e06-6d00-4ea8-b948-884c45f2b2a6(StopWatch.structure)" version="9" implicit="yes" />
  <root type="tpce.EnumerationDataTypeDeclaration" typeId="tpce.1082978164219" id="7555391427477566248" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="TimePrecision" />
    <property name="hasNoDefaultMember" nameId="tpce.1212080844762" value="true" />
    <link role="memberDataType" roleId="tpce.1083171729157" targetNodeId="tpck.1082983041843" resolveInfo="string" />
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7555391427477566249" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="min" />
      <property name="externalValue" nameId="tpce.1083923523172" value="minutes" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7555391427477566983" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="s" />
      <property name="externalValue" nameId="tpce.1083923523172" value="seconds " />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7555391427477566992" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="h" />
      <property name="externalValue" nameId="tpce.1083923523172" value="hours" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7555391427477567005" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="ms" />
      <property name="externalValue" nameId="tpce.1083923523172" value="milliseconds" />
    </node>
  </root>
  <root type="tpce.EnumerationDataTypeDeclaration" typeId="tpce.1082978164219" id="3271969819419415956" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="ShapeType" />
    <link role="memberDataType" roleId="tpce.1083171729157" targetNodeId="tpck.1082983041843" resolveInfo="string" />
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="3271969819419415957" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="rounded" />
      <property name="externalValue" nameId="tpce.1083923523172" value="circle form" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="3271969819419415958" nodeInfo="ig">
      <property name="externalValue" nameId="tpce.1083923523172" value="square form" />
      <property name="internalValue" nameId="tpce.1083923523171" value="squared" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391088954" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Dimensions" />
    <property name="conceptAlias" nameId="tpce.5092175715804935370" value="dimensions" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391088984" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="height" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391088986" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="width" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391088989" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="depth" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391151944" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Main" />
    <property name="rootable" nameId="tpce.1096454100552" value="true" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="implements" roleId="tpce.1169129564478" type="tpce.InterfaceConceptReference" typeId="tpce.1169127622168" id="7942873407391152034" nodeInfo="ig">
      <link role="intfc" roleId="tpce.1169127628841" targetNodeId="tpck.1169194658468" resolveInfo="INamedConcept" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391152038" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="stopwatch" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391152054" resolveInfo="StopWatch" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391152054" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="StopWatch" />
    <property name="abstract" nameId="tpce.4628067390765956802" value="true" />
    <property name="final" nameId="tpce.4628067390765956807" value="false" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391939635" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="dial" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391687212" resolveInfo="Dial" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391152079" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="dimensions" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391088954" resolveInfo="Dimensions" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391152063" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="weight" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391152064" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="form" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="3271969819419415956" resolveInfo="ShapeType" />
    </node>
    <node role="implements" roleId="tpce.1169129564478" type="tpce.InterfaceConceptReference" typeId="tpce.1169127622168" id="7942873407391720497" nodeInfo="ig">
      <link role="intfc" roleId="tpce.1169127628841" targetNodeId="tpck.1169194658468" resolveInfo="INamedConcept" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391478165" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Analog" />
    <property name="conceptAlias" nameId="tpce.5092175715804935370" value="analog" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391152054" resolveInfo="StopWatch" />
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391596002" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="button" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391595904" resolveInfo="Button" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391478170" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Digital" />
    <property name="conceptAlias" nameId="tpce.5092175715804935370" value="digital" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391152054" resolveInfo="StopWatch" />
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391562643" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="timePrecision" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="2155112052277249667" resolveInfo="Time" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391617888" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="startButton" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391595904" resolveInfo="Button" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391655464" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="stopButton" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391595904" resolveInfo="Button" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391655474" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="pauseButton" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391595904" resolveInfo="Button" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391655487" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="specialButtons" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="0..n" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391655507" resolveInfo="SpecialButtons" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="2155112052277249667" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Time" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391562636" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="value" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="2155112052277249668" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="precision" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="7555391427477566248" resolveInfo="TimePrecision" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391595904" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Button" />
    <property name="abstract" nameId="tpce.4628067390765956802" value="false" />
    <property name="final" nameId="tpce.4628067390765956807" value="false" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391595994" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="form" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="3271969819419415956" resolveInfo="ShapeType" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391637133" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="place" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391687224" resolveInfo="Coordinates" />
    </node>
  </root>
  <root type="tpce.EnumerationDataTypeDeclaration" typeId="tpce.1082978164219" id="7942873407391608479" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="ShowCurrentType" />
    <link role="memberDataType" roleId="tpce.1083171729157" targetNodeId="tpck.1082983657063" resolveInfo="boolean" />
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407391608480" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="false" />
      <property name="externalValue" nameId="tpce.1083923523172" value="no" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407391608481" nodeInfo="ig">
      <property name="externalValue" nameId="tpce.1083923523172" value="yes" />
      <property name="internalValue" nameId="tpce.1083923523171" value="true" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391608492" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="ShowCurrentType" />
    <property name="conceptAlias" nameId="tpce.5092175715804935370" value="showcurrenttime" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391655507" resolveInfo="SpecialButtons" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391608493" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="showTime" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="7942873407391608479" resolveInfo="ShowCurrentType" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391655507" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="SpecialButtons" />
    <property name="abstract" nameId="tpce.4628067390765956802" value="true" />
    <property name="final" nameId="tpce.4628067390765956807" value="false" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391595904" resolveInfo="Button" />
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391665334" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="LightButton" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391655507" resolveInfo="SpecialButtons" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391676291" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="light" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="7942873407391665406" resolveInfo="LightType" />
    </node>
  </root>
  <root type="tpce.EnumerationDataTypeDeclaration" typeId="tpce.1082978164219" id="7942873407391665406" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="LightType" />
    <link role="memberDataType" roleId="tpce.1083171729157" targetNodeId="tpck.1082983657063" resolveInfo="boolean" />
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407391665407" nodeInfo="ig">
      <property name="externalValue" nameId="tpce.1083923523172" value="turnOn" />
      <property name="internalValue" nameId="tpce.1083923523171" value="true" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407391665408" nodeInfo="ig">
      <property name="externalValue" nameId="tpce.1083923523172" value="turnOff" />
      <property name="internalValue" nameId="tpce.1083923523171" value="false" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391687212" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Dial" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391687213" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="form" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="3271969819419415956" resolveInfo="ShapeType" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407393069627" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="fontSize" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407391921963" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="place" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391687224" resolveInfo="Coordinates" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407391687224" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Coordinates" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="tpck.1133920641626" resolveInfo="BaseConcept" />
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391687278" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="leftX" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391687282" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="leftY" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407391687289" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="size" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="tpck.1082983657062" resolveInfo="integer" />
    </node>
  </root>
  <root type="tpce.ConceptDeclaration" typeId="tpce.1071489090640" id="7942873407393012337" nodeInfo="ig">
    <property name="name" nameId="tpck.1169194664001" value="Memorization" />
    <link role="extends" roleId="tpce.1071489389519" targetNodeId="7942873407391655507" resolveInfo="SpecialButtons" />
    <node role="linkDeclaration" roleId="tpce.1071489727083" type="tpce.LinkDeclaration" typeId="tpce.1071489288298" id="7942873407393012389" nodeInfo="ig">
      <property name="metaClass" nameId="tpce.1071599937831" value="aggregation" />
      <property name="role" nameId="tpce.1071599776563" value="button" />
      <property name="sourceCardinality" nameId="tpce.1071599893252" value="1" />
      <link role="target" roleId="tpce.1071599976176" targetNodeId="7942873407391595904" resolveInfo="Button" />
    </node>
    <node role="propertyDeclaration" roleId="tpce.1071489727084" type="tpce.PropertyDeclaration" typeId="tpce.1071489288299" id="7942873407393156784" nodeInfo="ig">
      <property name="name" nameId="tpck.1169194664001" value="memorization" />
      <link role="dataType" roleId="tpce.1082985295845" targetNodeId="7942873407393156722" resolveInfo="Memorization" />
    </node>
  </root>
  <root type="tpce.EnumerationDataTypeDeclaration" typeId="tpce.1082978164219" id="7942873407393156722" nodeInfo="ng">
    <property name="name" nameId="tpck.1169194664001" value="Memorization" />
    <property name="hasNoDefaultMember" nameId="tpce.1212080844762" value="true" />
    <link role="memberDataType" roleId="tpce.1083171729157" targetNodeId="tpck.1082983657063" resolveInfo="boolean" />
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407393156723" nodeInfo="ig">
      <property name="internalValue" nameId="tpce.1083923523171" value="false" />
      <property name="externalValue" nameId="tpce.1083923523172" value="not enable" />
    </node>
    <node role="member" roleId="tpce.1083172003582" type="tpce.EnumerationMemberDeclaration" typeId="tpce.1083171877298" id="7942873407393156775" nodeInfo="ig">
      <property name="externalValue" nameId="tpce.1083923523172" value="enable" />
      <property name="internalValue" nameId="tpce.1083923523171" value="true" />
    </node>
  </root>
</model>

