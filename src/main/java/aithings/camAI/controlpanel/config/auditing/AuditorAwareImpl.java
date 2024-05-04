package aithings.camAI.controlpanel.config.auditing;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import aithings.camAI.controlpanel.utils.WebUtil;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @NotNull
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.of(auth != null ? auth.getName() : WebUtil.getCurrentUsername());
    }
}
