/*
 * Copyright 2014 Yuriy Yunikov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ua.angrybeavers.material.em.storage;

import ua.angrybeavers.material.em.ui.items.RecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yyunikov
 */
public class Storage {

    private static Storage mInstance;

    private List<RecyclerViewItem> listItems = new ArrayList<>();

    private Storage() {
        listItems.add(new RecyclerViewItem("Personal Expense", "This is a default expense group"));
    }

    public static Storage getInstance() {
        if (mInstance == null) {
            mInstance = new Storage();
        }

        return mInstance;
    }

    public List<RecyclerViewItem> getListItems() {
        return listItems;
    }
}
