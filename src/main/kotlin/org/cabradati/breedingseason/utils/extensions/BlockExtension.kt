package org.cabradati.breedingseason.utils.extensions

import org.bukkit.block.Block
import org.bukkit.block.data.Ageable

fun Block.isAgeable(): Boolean {
    return this.blockData is Ageable
}