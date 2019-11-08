package hiram.liverpool.model

fun Record.toItem() = Item(this.productDisplayName, this.listPrice, this.lgImage)