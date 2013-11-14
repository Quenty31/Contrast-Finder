/*
 * Contrast Finder
 * Copyright (C) 2008-2013  Open-S Company
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */ 

package org.opens.color.finder.webapp.validator;

import java.awt.Color;
import org.opens.color.finder.webapp.model.ColorModel;
import org.opens.utils.colorconvertor.ColorConverter;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validateur pour le contenu de notre formulaire.
 *
 * @author Scub-Foundation
 */
public class ColorModelValidator implements Validator {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean supports(Class<?> clazz) {
        // Déclare les classes supportées par ce validateur
        return ColorModel.class.isAssignableFrom(clazz);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void validate(Object target, Errors errors) {
        ColorModel color = (ColorModel) target;
        String ratio = color.getRatio();
        String background = color.getBackground();
        String foreground = color.getForeground();
        validateColor("background", background, errors);
        validateColor("foreground", foreground, errors);
        validateRatio(ratio, errors);
    }

    private void validateRatio(String ratio, Errors errors) {
        if (!(isValidRatio(ratio) >= 1.0f
                && isValidRatio(ratio) <= 21.0f)) {
            errors.rejectValue("ratio", "NOT_A_VALID_RATIO", "Le ratio n'est pas valide");
        }
    }

    private Float isValidRatio(String ratio) {
        try {
            Float.parseFloat(ratio);
        } catch (NumberFormatException nfe) {
            return 0.0f;
        }
        Float coeff = Float.valueOf(ratio);
        if (coeff >= 1.0f && coeff <= 21.0f) {
            return coeff;
        } else {
            return Float.valueOf(0.0f);
        }
    }

    private void validateColor(String colorKey, String colorValue, Errors errors) {
        Color color = ColorConverter.hex2Rgb(colorValue);
        if (color == null) {
            errors.rejectValue(colorKey, "NOT_A_VALID_COLOR", "La couleur doit être entre #000000 à #FFFFFF");
        }
    }
}