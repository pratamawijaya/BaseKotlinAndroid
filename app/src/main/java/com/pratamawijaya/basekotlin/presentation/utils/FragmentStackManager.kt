package com.pratamawijaya.basekotlin.presentation.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.pratamawijaya.basekotlin.R

/**
 * Created by pratama on 8/4/17.
 */
class FragmentStackManager constructor(val activity: Activity, val manager: FragmentManager, val containerId: Int) {

    interface OnBackPressedHandlingFragment {
        fun onBackPressed(): Boolean
    }

    /**
     * Pushes a fragment to the top of the stack.
     */
    fun push(fragment: Fragment) {

        val top = peek()
        if (top != null) {
            manager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                    .remove(top)
                    .add(containerId, fragment, indexToTag(manager.backStackEntryCount + 1))
                    .addToBackStack(null)
                    .commit()
        } else {
            manager.beginTransaction()
                    .add(containerId, fragment, indexToTag(0))
                    .commit()
        }

        manager.executePendingTransactions()
    }

    /**
     * Pops the top item if the stack.
     * If the fragment implements [OnBackPressedHandlingFragment], calls [OnBackPressedHandlingFragment.onBackPressed] instead.
     * If [OnBackPressedHandlingFragment.onBackPressed] returns false the fragment gets popped.

     * @return true if a fragment has been popped or if [OnBackPressedHandlingFragment.onBackPressed] returned true;
     */
    fun back(): Boolean {
        val top = peek()
        if (top is OnBackPressedHandlingFragment) {
            if ((top as OnBackPressedHandlingFragment).onBackPressed())
                return true
        }
        return pop()
    }

    /**
     * Pops the topmost fragment from the stack.
     * The lowest fragment can't be popped, it can only be replaced.

     * @return false if the stack can't pop or true if a top fragment has been popped.
     */
    fun pop(): Boolean {
        if (manager.backStackEntryCount == 0)
            return false
        manager.popBackStackImmediate()
        return true
    }

    /**
     * Replaces stack contents with just one fragment.
     */
    fun replace(fragment: Fragment) {
        manager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        manager.beginTransaction()
                .replace(containerId, fragment, indexToTag(0))
                .commit()
        manager.executePendingTransactions()
    }

    /**
     * Returns the topmost fragment in the stack.
     */
    fun peek(): Fragment {
        return manager.findFragmentById(containerId)
    }

    fun <T> findCallback(fragment: Fragment, callbackType: Class<T>): T? {

        val back = getBackFragment(fragment)

        if (back != null && callbackType.isAssignableFrom(back.javaClass))
            return back as T?

        if (callbackType.isAssignableFrom(activity::class.java))
            return activity as T

        return null
    }

    private fun getBackFragment(fragment: Fragment): Fragment? {
        val fragments = getFragments()
        for (f in fragments.size - 1 downTo 0) {
            if (fragments[f] === fragment && f > 0)
                return fragments[f - 1]
        }
        return null
    }

    private fun getFragments(): List<Fragment> {
        val fragments = mutableListOf(Fragment())
        for (i in 0..manager.backStackEntryCount + 1 - 1) {
            val fragment = manager.findFragmentByTag(indexToTag(i))
            if (fragment != null)
                fragments.add(fragment)
        }
        return fragments
    }

    private fun indexToTag(index: Int): String {
        return Integer.toString(index)
    }
}