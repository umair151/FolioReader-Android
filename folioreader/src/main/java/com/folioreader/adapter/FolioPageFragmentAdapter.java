package com.folioreader.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.folioreader.Constants;
import com.folioreader.activity.FolioActivity;
import com.folioreader.fragments.FolioPageFragment;
import com.folioreader.smil.TextElement;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.SpineReference;

/**
 * Created by mahavir on 4/2/16.
 */
public class FolioPageFragmentAdapter extends FragmentStatePagerAdapter {
    private List<SpineReference> mSpineReferences;
    private Book mBook;
    private String mEpubFileName;
    private FolioPageFragment mFolioPageFragment;
    private ArrayList<TextElement> mTextElementArrayList;
    private boolean mIsSmileAvailable;

    public FolioPageFragmentAdapter(FragmentManager fm, List<SpineReference> spineReferences,
                                    Book book, String epubFilename) {
        super(fm);
        this.mSpineReferences = spineReferences;
        this.mBook = book;
        this.mEpubFileName = epubFilename;
        FolioActivity.BUS.register(this);
    }

    @Override
    public Fragment getItem(int position) {
        mFolioPageFragment = FolioPageFragment.newInstance(position, mBook, mEpubFileName, mTextElementArrayList, mIsSmileAvailable);
        mFolioPageFragment.setFragmentPos(position);
        return mFolioPageFragment;
    }

    @Override
    public int getCount() {
        return mSpineReferences.size();
    }

    @Subscribe
    public void setTextElementList(ArrayList<TextElement> textElementList) {
        if (textElementList != null && textElementList.size() > 0) {
            mIsSmileAvailable = true;
            mTextElementArrayList = textElementList;
        }
    }
}
