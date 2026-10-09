import Link from "next/link";
import type { ArticleSummary } from "@/lib/api";

const categoryLabel: Record<string, string> = {
  FICCION: "Ficción",
  CONSPIRACION: "Conspiración",
  TERROR: "Terror",
  MISTERIO: "Misterio",
  OTROS: "Otros",
};

export default function StoryCard({ article }: { article: ArticleSummary }) {
  return (
    <Link
      href={`/articulo/${article.slug}`}
      className="group flex flex-col overflow-hidden rounded-xl border border-white/10 bg-white/5 transition hover:border-brand-purple"
    >
      {article.imageUrl && (
        // eslint-disable-next-line @next/next/no-img-element
        <img
          src={article.imageUrl}
          alt={article.title}
          className="h-44 w-full object-cover"
        />
      )}
      <div className="flex flex-1 flex-col gap-2 p-4">
        <span className="w-fit rounded-full bg-brand-purple/20 px-3 py-1 text-xs text-brand-lilac">
          {categoryLabel[article.category] ?? article.category}
        </span>
        <h3 className="font-semibold text-brand-light group-hover:text-brand-lilac">
          {article.title}
        </h3>
        <p className="line-clamp-3 text-sm text-brand-light/70">{article.excerpt}</p>
      </div>
    </Link>
  );
}