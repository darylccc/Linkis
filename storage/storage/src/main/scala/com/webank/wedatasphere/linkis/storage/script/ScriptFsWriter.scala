/*
 * Copyright 2019 WeBank
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.wedatasphere.linkis.storage.script

import java.io.OutputStream

import com.webank.wedatasphere.linkis.common.io.{FsPath, FsWriter, MetaData}
import com.webank.wedatasphere.linkis.storage.LineRecord
import com.webank.wedatasphere.linkis.storage.script.compaction.{DefaultCompaction, PYScriptCompaction, QLScriptCompaction}
import com.webank.wedatasphere.linkis.storage.script.parser.{OtherScriptParser, PYScriptParser, QLScriptParser}
import com.webank.wedatasphere.linkis.storage.script.writer.StorageScriptFsWriter

abstract class ScriptFsWriter extends FsWriter {

  val path: FsPath
  val charset: String
  val outputStream: OutputStream

}

object ScriptFsWriter {
  def getScriptFsWriter(path: FsPath, charset: String,outputStream: OutputStream): ScriptFsWriter = new StorageScriptFsWriter(path, charset,outputStream)
}


object ParserFactory {
  def listParsers(): Array[Parser] = Array(PYScriptParser(), QLScriptParser(),OtherScriptParser())
}

object Compaction {
  def listCompactions(): Array[Compaction] = Array(PYScriptCompaction(),QLScriptCompaction(),DefaultCompaction())
}

trait Parser {
  def prefixConf:String

  def prefix: String

  def belongTo(suffix: String): Boolean

  def parse(line: String): Variable
}

trait Compaction {

  def prefisConf:String

  def prefix: String

  def belongTo(suffix: String): Boolean

  def compact(variable: Variable): String
}

class ScriptMetaData(var variables: Array[Variable]) extends MetaData {
  override def cloneMeta(): MetaData = new ScriptMetaData(variables)

  def getMetaData = variables

  def setMetaData(variables: Array[Variable]) = {
    this.variables = variables
  }
}

class ScriptRecord(line: String) extends LineRecord(line)

//definition  variable; specialConfiguration ;runConfiguration; startUpConfiguration;
case class Variable(sortParent:String,sort:String,key: String, value: String)

