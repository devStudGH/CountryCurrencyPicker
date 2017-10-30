/*
 * Copyright (C) 2017 Scrounger
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scrounger.countrycurrencypicker.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

public class CountryCurrencyButton extends android.support.v7.widget.AppCompatButton {
    private final String logTAG = CountryCurrencyAdapter.class.getName() + ".";

    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
        invalidate();
    }

    public void setCountry(String countryCode) {
        this.country = Country.getCountry(countryCode, getContext());
        invalidate();
    }

    public CountryCurrencyButton(Context context) {
        super(context);
    }

    public CountryCurrencyButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.countryCurrencyPicker);
        try {
            setCountry(a.getString(R.styleable.countryCurrencyPicker_country_code));
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (country != null) {
            setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(getContext(), country.getFlagId()), null, null, null);
            setText(country.getName());
        }
    }
}
