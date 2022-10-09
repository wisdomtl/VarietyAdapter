package taylor.com.paging3

import test.taylor.com.taylorcode.ui.recyclerview.variety.Text

class TextRepository {

     private var itemNumber: Int = 1

     fun getText(count:Int) = listOf( *(0 .. count).map { Text(itemNumber, "item ${itemNumber++}", 2) }.toTypedArray() )
}