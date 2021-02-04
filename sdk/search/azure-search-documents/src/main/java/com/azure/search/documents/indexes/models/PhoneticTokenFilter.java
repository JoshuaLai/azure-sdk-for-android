// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.search.documents.indexes.models;

import com.azure.core.rest.annotation.Fluent;
import com.azure.core.serde.SerdeProperty;
import com.azure.core.serde.SerdeTypeInfo;
import com.azure.core.serde.SerdeTypeName;

/**
 * Create tokens for phonetic matches. This token filter is implemented using
 * Apache Lucene.
 */
@SerdeTypeInfo(use = SerdeTypeInfo.Id.NAME, include = SerdeTypeInfo.As.PROPERTY, property = "@odata.type")
@SerdeTypeName("#Microsoft.Azure.Search.PhoneticTokenFilter")
@Fluent
public final class PhoneticTokenFilter extends TokenFilter {
    /*
     * The phonetic encoder to use. Default is "metaphone". Possible values
     * include: 'Metaphone', 'DoubleMetaphone', 'Soundex', 'RefinedSoundex',
     * 'Caverphone1', 'Caverphone2', 'Cologne', 'Nysiis', 'KoelnerPhonetik',
     * 'HaasePhonetik', 'BeiderMorse'
     */
    @SerdeProperty(value = "encoder")
    private PhoneticEncoder encoder;

    /*
     * A value indicating whether encoded tokens should replace original
     * tokens. If false, encoded tokens are added as synonyms. Default is true.
     */
    @SerdeProperty(value = "replace")
    private Boolean originalTokensReplaced;

    /**
     * Constructor of {@link PhoneticTokenFilter}.
     *
     * @param name The name of the token filter. It must only contain letters, digits,
     * spaces, dashes or underscores, can only start and end with alphanumeric
     * characters, and is limited to 128 characters.
     */
    public PhoneticTokenFilter(String name) {
        super(name);
    }

    /**
     * Get the encoder property: The phonetic encoder to use. Default is
     * "metaphone". Possible values include: 'Metaphone', 'DoubleMetaphone',
     * 'Soundex', 'RefinedSoundex', 'Caverphone1', 'Caverphone2', 'Cologne',
     * 'Nysiis', 'KoelnerPhonetik', 'HaasePhonetik', 'BeiderMorse'.
     *
     * @return the encoder value.
     */
    public PhoneticEncoder getEncoder() {
        return this.encoder;
    }

    /**
     * Set the encoder property: The phonetic encoder to use. Default is
     * "metaphone". Possible values include: 'Metaphone', 'DoubleMetaphone',
     * 'Soundex', 'RefinedSoundex', 'Caverphone1', 'Caverphone2', 'Cologne',
     * 'Nysiis', 'KoelnerPhonetik', 'HaasePhonetik', 'BeiderMorse'.
     *
     * @param encoder the encoder value to set.
     * @return the PhoneticTokenFilter object itself.
     */
    public PhoneticTokenFilter setEncoder(PhoneticEncoder encoder) {
        this.encoder = encoder;
        return this;
    }

    /**
     * Get the replaceOriginalTokens property: A value indicating whether
     * encoded tokens should replace original tokens. If false, encoded tokens
     * are added as synonyms. Default is true.
     *
     * @return the replaceOriginalTokens value.
     */
    public Boolean areOriginalTokensReplaced() {
        return this.originalTokensReplaced;
    }

    /**
     * Set the replaceOriginalTokens property: A value indicating whether
     * encoded tokens should replace original tokens. If false, encoded tokens
     * are added as synonyms. Default is true.
     *
     * @param originalTokensReplaced the replaceOriginalTokens value to set.
     * @return the PhoneticTokenFilter object itself.
     */
    public PhoneticTokenFilter setOriginalTokensReplaced(Boolean originalTokensReplaced) {
        this.originalTokensReplaced = originalTokensReplaced;
        return this;
    }
}