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
/**
 * Created by allenlliu on 2018/12/5.
 */
package com.webank.wedatasphere.linkis.entrance.exception;

import com.webank.wedatasphere.linkis.common.exception.ErrorException;

/**
 * @Title: EntranceFileNotFoundException
 * @ProjectName linkis-Spark
 * @author georgeqiao
 * @date 2018/10/28 10:47
 * @Description: TODO
 */
public class EntranceFileNotFoundException extends ErrorException {
    public EntranceFileNotFoundException(int errCode, String message){
        super(errCode, message);
    }
}
